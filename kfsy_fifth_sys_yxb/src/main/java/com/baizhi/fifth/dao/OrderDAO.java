package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Order;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface OrderDAO extends BaseDAO<Order> {
    //根据订单状态查询订单(已完成,未支付)
    List<Order> queryByStatus(String status);
    //查询未评价订单
    List<Order> queryNoneInfo();
}
