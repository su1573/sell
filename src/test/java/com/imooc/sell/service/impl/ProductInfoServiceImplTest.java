package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Sort.Direction.ASC;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 19:07
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;
    @Test
    public void findOne() throws Exception{
        ProductInfo productInfo = productInfoService.findOne("12342");
        System.out.println(productInfo);

    }

    @Test
    public void findUpAll() throws Exception{
        List<ProductInfo> list = productInfoService.findUpAll();

        System.out.println(list);
    }

    @Test
    public   void findAll() throws Exception{
        PageRequest request = PageRequest.of(0, 10);
        Page<ProductInfo> page = productInfoService.findAll(request);
        System.out.println(page.getContent());
        System.out.println(page.getTotalElements());



    }

    @Test
    public void save() throws Exception{
        ProductInfo productInfo = new ProductInfo();

        productInfo.setProductId("1111");
        productInfo.setProductName("螺蛳粉");
        productInfo.setProductPrice(new BigDecimal(16.5));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("百吃不厌，店长推荐");
        productInfo.setProductIcon("http://xxxx.com");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(3);

        ProductInfo productInfo1 = productInfoService.save(productInfo);
        System.out.println(productInfo1);

    }
}