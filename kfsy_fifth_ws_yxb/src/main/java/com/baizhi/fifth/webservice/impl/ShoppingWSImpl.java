package com.baizhi.fifth.webservice.impl;


import com.baizhi.fifth.dao.OrderDAO;
import com.baizhi.fifth.dao.OrderItemDAO;
import com.baizhi.fifth.dao.ProductDAO;
import com.baizhi.fifth.entity.*;
import com.baizhi.kfsy.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import java.util.*;

@Controller
@RequestMapping("/shopping")
public class ShoppingWSImpl{
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private OrderItemDAO orderItemDAO;

    @RequestMapping(value="/cart/{userId}/",method = RequestMethod.GET)
    @ResponseBody
    public List<CartItem> queryCartByUser(@PathVariable("userId")String userId) {
        // userId:check
        //              productId   cartItem
        //从redis里面获取当前用户的购物车
        ArrayList<CartItem> list =new ArrayList<CartItem>();
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map entries1 = hashOperations.entries("cart"+userId + ":check");
        Map entries2 = hashOperations.entries("cart"+userId + ":uncheck");
        for (Object o : entries1.values()) {
            list.add((CartItem) o);
        }
        for (Object o : entries2.values()) {
            list.add((CartItem) o);
        }
        return list;
    }
    @RequestMapping(value="/cart/add/{userId}/{productId}/",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap<String, Object> insertOneProduct(@PathVariable("productId")String productId, @PathVariable("userId")String userId) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        Product product = productDAO.queryOne(productId);
        //生成购物项
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCount(1);
        cartItem.setSub(product.getPrice());
        //把购物项放入到redis里面
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("cart" + userId + ":check",productId,cartItem);
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/cart/plus/{userId}/{productId}/",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap<String, Object> plusProduct(@PathVariable("productId")String productId, @PathVariable("userId")String userId) {
        //从购物车里面查出这个购物项,数量加1
        HashOperations hashOperations = redisTemplate.opsForHash();
        CartItem cartItem = (CartItem) hashOperations.get("cart" + userId + ":check", productId);
        cartItem.setCount(cartItem.getCount()+1);
        hashOperations.put("cart" + userId + ":check",productId,cartItem);
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/cart/minu/{userId}/{productId}/",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap<String, Object> minuProduct(@PathVariable("productId")String productId, @PathVariable("userId")String userId) {
        //从购物车里面查出这个购物项,数量减1
        HashOperations hashOperations = redisTemplate.opsForHash();
        CartItem cartItem = (CartItem) hashOperations.get("cart" + userId + ":check", productId);
        cartItem.setCount(cartItem.getCount()-1);
        hashOperations.put("cart" + userId + ":check",productId,cartItem);
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/cart/del/{userId}/{productId}/",method = RequestMethod.DELETE)
    @ResponseBody
    public HashMap<String, Object> deleteProduct(@PathVariable("productId")String productId, @PathVariable("userId")String userId) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.delete("cart" + userId + ":check",productId);
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/cart/selectItem/{userId}/{productId}/{status}/",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> selectItem(@PathVariable("productId")String productId, @PathVariable("userId")String userId,@PathVariable("status")String status) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        String obey;
        if(status.equals("check")){
            obey="uncheck";
        }else{
            obey="check";
        }
        CartItem cartItem = (CartItem) hashOperations.get("cart" + userId + ":"+status, productId);
        hashOperations.put("cart" + userId + ":"+obey,productId,cartItem);
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/cart/createOrder/{productId}/",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> createOrder(@PathVariable("productId")String userId,@RequestBody Address address) {
        //从redis里面获取当前用户的购物车
        ArrayList<OrderItem> list =new ArrayList<OrderItem>();
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map entries1 = hashOperations.entries("cart" + userId + ":check");
        //转换成订单项
        for (Object o : entries1.values()) {
            CartItem cartItem=(CartItem) o;
            OrderItem orderItem=new OrderItem();
            orderItem.setId(UUID.randomUUID().toString());
            orderItem.setCount(cartItem.getCount());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setSub(cartItem.getSub());
            list.add(orderItem);
            //订单项入库
            orderItemDAO.insert(orderItem);
        }
        Order order=new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        order.setStatus("未支付");
        order.setAddress(address);
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        order.setNum(String.valueOf(idWorker.nextId()));
        User user=new User();
        user.setId(userId);
        order.setUser(user);
        order.setOrderItems(list);
        orderDAO.insert(order);
        //清空购物车
        redisTemplate.delete("cart" + userId + ":check");
        HashMap<String, Object> map=new HashMap<String, Object>();
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/cart/deleteOrder/{orderId}/",method = RequestMethod.DELETE)
    @ResponseBody
    public HashMap<String, Object> deleteOrder(@PathVariable("orderId")String orderId) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        orderDAO.delete(orderId);
        map.put("success",true);
        return map;
    }

    /**
     * 订单支付
     * @param orderId
     * @return
     */
    public HashMap<String, Object> buyOrder(String orderId) {
        return null;
    }
}
