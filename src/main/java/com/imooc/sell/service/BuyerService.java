package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDTO;

/**
 * @program: sell
 * @Date: 2020/4/13 0013 23:42
 * @Author: Mr.SU
 * @Description:买家
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
