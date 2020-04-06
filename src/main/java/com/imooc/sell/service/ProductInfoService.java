package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 18:56
 * @Author: Mr.SU
 * @Description:
 */
public interface ProductInfoService {

    /*根据商品id查询*/
    ProductInfo findOne(String productId) throws Exception;

    /*查询所有在架商品*/
    List<ProductInfo> findUpAll() throws Exception;

    Page<ProductInfo> findAll(Pageable pageable) throws Exception;

    ProductInfo save(ProductInfo productInfo) throws Exception;

    /*加库存*/

    /*减库存*/


}
