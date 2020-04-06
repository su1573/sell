package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.ProductInfo;
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
 * @Date: 2020/4/5 0005 18:45
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryDaoTest {

    @Autowired
    private ProductInfoRepositoryDao productInfoRepositoryDao;

    @Test
    public void saveInfo(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("12342");
        productInfo.setProductName("年糕");
        productInfo.setProductPrice(new BigDecimal(9.25));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("百吃不厌，店长推荐");
        productInfo.setProductIcon("http://xxxx.com");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);

        ProductInfo productInfo1 = productInfoRepositoryDao.save(productInfo);
        System.out.println(productInfo1);

    }

    @Test
    public void  findByProductStatus() throws Exception{

        List<ProductInfo> list =  productInfoRepositoryDao.findByProductStatus(0);
        System.out.println(list);


    }

}