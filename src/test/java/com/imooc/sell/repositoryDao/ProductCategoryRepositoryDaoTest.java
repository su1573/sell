package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.ProductCategory;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Proxy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;


/**
 * @program: sell
 * @Date: 2020/4/5 0005 0:39
 * @Author: Mr.SU
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryDaoTest {

    @Autowired
    private ProductCategoryRepositoryDao repository;

    @Test
    public void findOneTest(){

        //hibernate默认懒加载为true，productCategory为代理对象，并不直接操作数据库，
        ProductCategory productCategory = repository.getOne(1);

        System.out.println(productCategory.toString());
    }

    @Test
    public void saveInfo1(){
        ProductCategory productCategory = repository.getOne(2);
        productCategory.setCategoryName("FE最爱");
        repository.save(productCategory);
    }

    @Test
    public void saveinfo2(){

        ProductCategory productCategory = new ProductCategory("时尚风格",3);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
//        Assert.assertNotEquals(null,result);
    }

    @Test
    public void testFindBy(){
        List<Integer> list = Arrays.asList(2,3,4);

        List<ProductCategory> result =  repository.findByCategoryTypeIn(list);

        Assert.assertNotEquals(0,result.size());

    }



}