package com.imooc.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 16:54
 * @Author: Mr.SU
 * @Description:
 */
@Entity
@Data
public class OrderDetail {

    /**/
    @Id
    private String detailId;

    /*订单id*/
    private String orderId;

    /*商品Id*/
    private String productId;

    /*商品名称*/
    private String productName;

    /*商品单价*/
    private BigDecimal productPrice;

    /*商品数量*/
    private Integer productQuantity;

    /*商品小图*/
    private String productIcon;




}

