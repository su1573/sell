package com.imooc.sell.service.impl;

import com.imooc.sell.converter.OrderMasterToOrderDTOConverter;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repositoryDao.OrderDetailRepositoryDao;
import com.imooc.sell.repositoryDao.OrderMasterRepositoryDao;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.print.DocFlavor;
import javax.transaction.Transactional;
import java.beans.Transient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 18:12
 * @Author: Mr.SU
 * @Description:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepositoryDao orderDetailRepositoryDao;

    @Autowired
    private OrderMasterRepositoryDao orderMasterRepositoryDao;

    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO){

        String orderId = KeyUtil.getUniqueKey();
        try {
            BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
            //1、查询商品（数量、价格）
            for (OrderDetail orderDetail: orderDTO.getOrderDetailList()
                 ) {
                ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
                if(productInfo == null){
                    throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                }
                //2、计算订单总价
                orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

                //入库
                orderDetail.setDetailId(KeyUtil.getUniqueKey());
                orderDetail.setOrderId(orderId);
                BeanUtils.copyProperties(productInfo,orderDetail);  //java8新特性，把一个类的属性赋值到另一个类的属性
                OrderDetail result =  orderDetailRepositoryDao.save(orderDetail);
                System.out.println("---------------------保存订单详情表："+result);
            }

            //3、写入订单数据库（OrderMaster和OrderDeatil）
            OrderMaster orderMaster = new OrderMaster();
            orderDTO.setOrderId(orderId);
            BeanUtils.copyProperties(orderDTO,orderMaster);
            orderMaster.setOrderAmount(orderAmount);
            OrderMaster result =  orderMasterRepositoryDao.save(orderMaster);
            System.out.println("---------------------保存订单主表："+result);

            //4、扣库存
            List<CartDTO> cartDTOList = new ArrayList<>();
            cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());

            productInfoService.decreaseStock(cartDTOList);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=  orderMasterRepositoryDao.getOne(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepositoryDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);


        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOopenId, Pageable pageable) {
        Page<OrderMaster> orderDTOS = orderMasterRepositoryDao.findByBuyerOpenid(buyerOopenId,pageable);

        List<OrderDTO>  orderDTOList=  OrderMasterToOrderDTOConverter.convert(orderDTOS.getContent());

        Page<OrderDTO> page = new PageImpl<OrderDTO>(orderDTOList,pageable,orderDTOS.getTotalElements());

        return page;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        //先判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERD_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMasterUP = orderMasterRepositoryDao.save(orderMaster);
        if (orderMasterUP == null) {
            log.error("【取消订单】更新失败，orderMasterUP={}",orderMasterUP);
            throw new SellException(ResultEnum.ORDERD_UPDATE_FAIL);
        }else{
            BeanUtils.copyProperties(orderMaster,orderDTO);
            System.out.println(orderMasterUP);
        }

        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】订单中无商品信息，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDERD_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productInfoService.increaseStock(cartDTOList);

        //若已支付，需退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO

        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finash(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERD_STATUS_ERROR);
        }

        //修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster update = orderMasterRepositoryDao.save(orderMaster);
        if (update == null) {
            log.error("【完结订单】更新失败，update={}",update);
            throw new SellException(ResultEnum.ORDERD_UPDATE_FAIL);
        }else{
//            BeanUtils.copyProperties(update,orderDTO);
            System.out.println(orderDTO);
        }


        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付完成】订单状态不正确，orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERD_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【订单支付完成】订单支付状态不正确，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDERD_PAY_STATUS_ERROR);
        }

        //修改订单支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster update = orderMasterRepositoryDao.save(orderMaster);
        if (update == null) {
            log.error("【订单支付完成】更新失败，update={}",update);
            throw new SellException(ResultEnum.ORDERD_UPDATE_FAIL);
        }else{
//            BeanUtils.copyProperties(update,orderDTO);
            System.out.println(orderDTO);
        }

        return orderDTO;
    }
}
