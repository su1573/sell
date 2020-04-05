package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 15:44
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;
    @Test
    public void findOne() {
        ProductCategory productCategory = productCategoryService.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = productCategoryService.findAll();
        System.out.println(list.size());

    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> liIn = Arrays.asList(1,2,3);
        List<ProductCategory> list = productCategoryService.findByCategoryTypeIn(liIn);
        System.out.println(list.size());

    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("冬季新款",4);
        productCategoryService.save(productCategory);





    }
}