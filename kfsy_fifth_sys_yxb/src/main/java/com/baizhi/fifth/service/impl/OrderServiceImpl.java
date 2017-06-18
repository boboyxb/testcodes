package com.baizhi.fifth.service.impl;


import com.baizhi.fifth.dao.OrderDAO;
import com.baizhi.fifth.entity.Order;
import com.baizhi.fifth.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDAO orderDAO;

    private Page<Order> pages;

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Order> queryAll(Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        orderDAO.queryAll();
        return pages;
    }

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Order> queryPayed(Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        orderDAO.queryLike("status","已支付");
        return pages;
    }

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Order> queryUnpayed(Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        orderDAO.queryLike("status","未支付");
        return pages;
    }

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Order> queryByUserId(Integer rows, Integer page, String userId) {
        pages= PageHelper.startPage(page,rows);
        orderDAO.queryByUserId(userId);
        return pages;
    }

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Order queryOne(String id) {
        Order order = orderDAO.queryOne(id);
        return order;
    }

    public void updateOrderAddress(Order order) {
        orderDAO.updateOrderAddress(order);
    }
}
