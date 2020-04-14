package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 16:47
 * @Author: Mr.SU
 * @Description:
 */
@Getter
public enum OrderStatusEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}
