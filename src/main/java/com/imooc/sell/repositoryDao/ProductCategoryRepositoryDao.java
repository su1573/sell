package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 0:37
 * @Author: Mr.SU
 * @Description:
 */
public interface ProductCategoryRepositoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
