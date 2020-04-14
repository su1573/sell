package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDTO;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 19:03
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "sph520720";
    private final String ORDERID = "1586173092204802143";
    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小苏");
        orderDTO.setBuyerAddress("苏家护院");
        orderDTO.setBuyerPhone("13426094461");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1111");
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("12342");
        orderDetail1.setProductQuantity(3);
        orderDetailList.add(orderDetail1);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result  = orderService.create(orderDTO);

        log.info("【创建订单】 result={} ："+result);

    }

    @Test
    public void getOne() {

        OrderDTO orderDTO = orderService.findOne(ORDERID);
        System.out.println(orderDTO);

    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderDTO> orderDTOS =  orderService.findList(BUYER_OPENID,pageRequest);

        System.out.println(orderDTOS.getContent());
        System.out.println(orderDTOS.getTotalElements());

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTO2 = orderService.cancel(orderDTO);
        System.out.println(orderDTO2);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),orderDTO2.getOrderStatus());

    }

    @Test
    public void finash() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTO2 = orderService.finash(orderDTO);
        System.out.println(orderDTO2);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),orderDTO2.getOrderStatus());

    }

    @Test
    public void paid() {

        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO orderDTO2 = orderService.paid(orderDTO);
        System.out.println(orderDTO2);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),orderDTO2.getPayStatus());
    }
}