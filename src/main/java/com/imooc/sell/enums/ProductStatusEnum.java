package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 19:01
 * @Author: Mr.SU
 * @Description: 商品状态
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架");


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
