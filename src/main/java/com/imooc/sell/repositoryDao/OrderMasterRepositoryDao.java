package com.imooc.sell.repositoryDao;

import com.imooc.sell.dataobject.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: sell
 * @Date: 2020/4/6 0006 16:59
 * @Author: Mr.SU
 * @Description:
 */
public interface OrderMasterRepositoryDao extends JpaRepository<OrderMaster,String> {

    //根据买家微信Id,分页查询订单
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
