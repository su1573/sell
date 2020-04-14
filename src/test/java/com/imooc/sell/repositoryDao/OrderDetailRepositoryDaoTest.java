package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 17:08
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryDaoTest {

    @Autowired
    private OrderDetailRepositoryDao orderDetailRepositoryDao;

    @Test
    public void saceTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("202004061750");
        orderDetail.setOrderId("878787");
        orderDetail.setProductIcon("http://xxx.xom");
        orderDetail.setProductId("124211");
        orderDetail.setProductName("花甲粉");
        orderDetail.setProductPrice(new BigDecimal(16.6));
        orderDetail.setProductQuantity(2);

        OrderDetail result = orderDetailRepositoryDao.save(orderDetail);
        System.out.println(result);
    }

    @Test
    public void findByBuyerOpenid(){

        List<OrderDetail> result = orderDetailRepositoryDao.findByOrderId("878787");
        System.out.println(result);

    }
}