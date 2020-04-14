package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 16:51
 * @Author: Mr.SU
 * @Description:
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
