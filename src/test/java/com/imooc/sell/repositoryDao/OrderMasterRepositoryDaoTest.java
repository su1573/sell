package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 17:07
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryDaoTest {

    @Autowired
    private OrderMasterRepositoryDao orderMasterRepositoryDao;

    private final String OPENID= "sph520720";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("22222");
        orderMaster.setBuyerName("小苏2");
        orderMaster.setBuyerPhone("13426094461");
        orderMaster.setBuyerAddress("刘家护院");
        orderMaster.setBuyerOpenid("liu520720");
        orderMaster.setOrderAmount(new BigDecimal(66.66));

        OrderMaster result = orderMasterRepositoryDao.save(orderMaster);
        System.out.println(result);
    }

    @Test
    public void findByBuyerOpenid(){

        PageRequest pageRequest = PageRequest.of(0,10);
        Page<OrderMaster> result = orderMasterRepositoryDao.findByBuyerOpenid(OPENID,pageRequest);
        System.out.println(result.getContent());
        System.out.println(result.getTotalElements());


    }

}