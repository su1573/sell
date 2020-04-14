package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 18:20
 * @Author: Mr.SU
 * @Description:
 */
public class SellException extends RuntimeException {

    private Integer code;

    private String message;


    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }

}
