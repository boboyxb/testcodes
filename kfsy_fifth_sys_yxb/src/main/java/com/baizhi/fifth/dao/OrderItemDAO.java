package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.OrderItem;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface OrderItemDAO extends BaseDAO<OrderItem> {
    //根据订单id查询订单项
    List<OrderItem> queryByOrderId(String id);
}
