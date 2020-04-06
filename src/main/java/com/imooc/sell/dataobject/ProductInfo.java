package com.imooc.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @program: sell
 * @Date: 2020/4/5 0005 18:37
 * @Author: Mr.SU
 * @Description:
 */
@Entity
@Data
@Proxy(lazy=false)
public class ProductInfo {
    @Id
    private String productId;

    /*名字*/
    private String productName;

    /*单价*/
    private BigDecimal productPrice;

    /*库存*/
    private Integer productStock;

    /*描述*/
    private String productDescription;

    /*小图*/
    private String productIcon;

    /*状态 0-正常 1-下架*/
    private Integer productStatus;

    /*类目编号*/
    private Integer categoryType;


}
