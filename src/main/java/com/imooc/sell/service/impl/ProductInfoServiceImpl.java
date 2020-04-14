package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDTO;
import com.imooc.sell.enums.ProductStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repositoryDao.ProductInfoRepositoryDao;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 19:00
 * @Author: Mr.SU
 * @Description:
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepositoryDao productInfoRepositoryDao;

    @Override
    public ProductInfo findOne(String productId) throws Exception {
        return productInfoRepositoryDao.getOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() throws Exception {
        return productInfoRepositoryDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) throws Exception {
        return productInfoRepositoryDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) throws Exception {
        return productInfoRepositoryDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO: cartDTOList
             ) {
            ProductInfo productInfo = productInfoRepositoryDao.getOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepositoryDao.save(productInfo);
        }


    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList
             ) {
            ProductInfo productInfo = productInfoRepositoryDao.getOne(cartDTO.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer count = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if(count < 0 ){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(count);

            productInfoRepositoryDao.save(productInfo);
        }

    }
}
