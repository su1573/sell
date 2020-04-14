package com.imooc.sell.dto;

import lombok.Data;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 18:46
 * @Author: Mr.SU
 * @Description:
 */
@Data
public class CartDTO {

    private String productId;

    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
