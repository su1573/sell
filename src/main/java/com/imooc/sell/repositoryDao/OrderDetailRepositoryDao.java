package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 17:04
 * @Author: Mr.SU
 * @Description:
 */
public interface OrderDetailRepositoryDao extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String orderId);

}

