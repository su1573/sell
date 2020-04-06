package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 18:42
 * @Author: Mr.SU
 * @Description:
 */

public interface ProductInfoRepositoryDao extends JpaRepository<ProductInfo, String> {

    /*查询商品的状态*/
    List<ProductInfo> findByProductStatus(Integer productStatus) throws Exception;






}
