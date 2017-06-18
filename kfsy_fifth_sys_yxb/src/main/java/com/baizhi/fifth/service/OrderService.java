package com.baizhi.fifth.service;


import com.baizhi.fifth.entity.Order;
import com.github.pagehelper.Page;

public interface OrderService {
    //查询所有订单
    Page<Order> queryAll(Integer rows, Integer page);
    //查询已支付订单
    Page<Order> queryPayed(Integer rows, Integer page);
    //查询未支付订单
    Page<Order> queryUnpayed(Integer rows, Integer page);
    //查询某一用户的所有订单
    Page<Order> queryByUserId(Integer rows, Integer page,String userId);
    //根据id查询一个订单
    Order queryOne(String id);
    //修改订单的地址
    void updateOrderAddress(Order order);
}
