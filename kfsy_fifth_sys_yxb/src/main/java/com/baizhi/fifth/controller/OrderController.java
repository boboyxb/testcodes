package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Order;
import com.baizhi.fifth.entity.OrderItem;
import com.baizhi.fifth.service.OrderService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    /**
     * 查询全部订单
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public HashMap<String,Object> queryAll(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Order> userPage = orderService.queryAll(rows, page);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }
    /**
     * 根据用户id查询所有订单
     */
    @RequestMapping("/queryByUserId")
    @ResponseBody
    public HashMap<String,Object> queryByUserId(String id,Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Order> userPage = orderService.queryByUserId(rows, page,id);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }
    /**
     * 查询已支付订单
     */
    @RequestMapping("/queryPayed")
    @ResponseBody
    public HashMap<String,Object> queryPayed(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Order> userPage = orderService.queryPayed(rows,page);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }
    /**
     * 查询未支付订单
     */
    @RequestMapping("/queryUnPayed")
    @ResponseBody
    public HashMap<String,Object> queryUnPayed(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Order> userPage = orderService.queryUnpayed(rows,page);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }
    /**
     * 查询一个订单
     */
    @RequestMapping("/queryOne")
    @ResponseBody
    public Order queryOne(String id){
        Order order = orderService.queryOne(id);
        return order;
    }
    /**
     * 查询一个订单下的订单项
     */
    @RequestMapping("/queryOrderItems")
    @ResponseBody
    public List<OrderItem> queryOrderItems(String id){
        Order order = orderService.queryOne(id);
        System.out.println(id);
        System.out.println(order);
        return order.getOrderItems();
    }
    /**
     * 更新订单的地址
     */
    @RequestMapping("/updateOrderAddress")
    @ResponseBody
    public HashMap<String, Object> updateOrderAddress(Order order){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
           orderService.updateOrderAddress(order);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","暂时无法更新,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }

}
