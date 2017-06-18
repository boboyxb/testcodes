package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDAO extends BaseDAO<Order> {
    //模糊查询订单
    List<Order> queryLike(@Param("type") String type, @Param("content") String content);
    //查询某个用户的所有订单
    List<Order> queryByUserId(String id);
    //修改订单地址
    void updateOrderAddress(Order order);
    //查询所有订单
}
