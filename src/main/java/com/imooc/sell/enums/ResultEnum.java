package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 18:21
 * @Author: Mr.SU
 * @Description:
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDERD_STATUS_ERROR(14,"订单状态不正确"),
    ORDERD_UPDATE_FAIL(15,"订单更新失败"),
    ORDERD_DETAIL_EMPTY(16,"订单商品不存在"),
    ORDERD_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    PARM_ERROR(1,"参数不正确"),
    CART_EMPTY(18,"购物车不能为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户")
    ;



    ResultEnum(Integer code, String message) {
        this.code = code;
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

}
