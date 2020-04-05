package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.repositoryDao.ProductCategoryRepositoryDao;
import com.imooc.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 15:39
 * @Author: Mr.SU
 * @Description:
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepositoryDao repositoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repositoryDao.getOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repositoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repositoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repositoryDao.save(productCategory);
    }
}
