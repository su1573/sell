package com.imooc.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 18:09
 * @Author: Mr.SU
 * @Description:
 */
@Data
@DynamicUpdate
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    /*订单id*/
    private String orderId;
    /*买家名字*/
    private String buyerName;
    /*买家手机号*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*买家微信id*/
    private String buyerOpenid;

    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态，默认为0新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /*支付状态，默认为0未支付*/
    private Integer payStatus = OrderStatusEnum.NEW.getCode();


    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
