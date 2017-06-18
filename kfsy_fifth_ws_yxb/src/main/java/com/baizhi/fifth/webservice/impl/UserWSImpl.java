package com.baizhi.fifth.webservice.impl;


import com.baizhi.fifth.entity.Address;
import com.baizhi.fifth.entity.User;
import com.baizhi.fifth.service.impl.UserServiceWS;
import com.baizhi.kfsy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserWSImpl{
    @Autowired
    private UserServiceWS userServiceWS;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value="/login/{phone}/{password}/",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> loginUsePwd(@PathVariable("phone")String phone, @PathVariable("password")String password) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        User user = null;
        //验证登陆
        try {
            user = userServiceWS.login(phone, password);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message",e.getMessage());
        }
        map.put("success",true);
        //保持会话,生成token令牌
        String token= MD5Utils.getMd5Code(UUID.randomUUID().toString());
            //将用户的id和token作为key放入redis中
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(user.getId(),token,user);
        map.put("token",token);
        map.put("user",user);
        return map;
    }
    @RequestMapping(value="/verifyCode/{phone}/",method = RequestMethod.GET)
    @ResponseBody
    public HashMap<String, Object> verifyCode(@PathVariable("phone")String phone) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        //查看该手机号验证码是否已经发送
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(phone);
        if(o!=null){
            Long expire = redisTemplate.getExpire(phone);
            map.put("success",false);
            map.put("message","请在"+expire+"秒后重新发送");
            return map;
        }
        //发送验证码
        String salt = userServiceWS.verifyCode(phone);
        //将验证码放入redis缓存中
        //设置验证码失效时间--60秒
        valueOperations.set(phone,salt);
        redisTemplate.expireAt(phone,new Date(60000));
        map.put("success",true);
        System.out.println(salt);
        return map;
    }
    @RequestMapping(value="/regist/{phone}/{salt}",method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> regist(@RequestBody User user,@PathVariable("phone")String phone,@PathVariable("salt")String salt) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        //从redis中拿出该手机号的验证码
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(phone);
        if(o==null){
            map.put("success",false);
            map.put("message","验证码失效,请重新获取");
            return map;
        }
        //比对验证码
        if(!o.toString().equals(salt)){
            map.put("success",false);
            map.put("message","验证码错误,请重新获取");
            return map;
        }
        User userDB = userServiceWS.regist(user);
        map.put("success",true);
        map.put("user",userDB);
        //保持会话,生成token令牌
        String token= MD5Utils.getMd5Code(UUID.randomUUID().toString());
        //将用户的id和token作为key放入redis中
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put(user.getId(),token,user);
        map.put("token",token);
        return map;
    }

    @RequestMapping(value="/address/query/{userId}/",method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<Address> queryAddressByUser(@PathVariable("userId")String userId) {
        ArrayList<Address> addresses = userServiceWS.queryAddressByUser(userId);
        return addresses;
    }
    @RequestMapping(value="/address/insert/{userId}/",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap<String, Object> insertAddress(@PathVariable("userId")String userId,@RequestBody Address address) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        //添加地址后,自动设置为用户的默认收货地址
        try {
            userServiceWS.insertAddress(userId,address);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","添加地址失败,请稍后再试");
            return map;
        }
        map.put("success",true);
        return map;
    }
    @RequestMapping(value="/address/delete/{addressId}/",method = RequestMethod.DELETE)
    @ResponseBody
    public HashMap<String, Object> deleteAddress(@PathVariable("addressId")String addressId) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        try {
            userServiceWS.deleteAddress(addressId);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","删除地址失败,请稍后再试");
            return map;
        }
        map.put("success",true);
        return map;
    }

    @RequestMapping(value="/address/update/",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap<String, Object> updateAddress(@RequestBody Address address) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        try {
            userServiceWS.updateAddress(address);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","修改地址失败,请稍后再试");
            return map;
        }
        map.put("success",true);
        return map;
    }

    @RequestMapping(value="/update/",method = RequestMethod.PUT)
    @ResponseBody
    public HashMap<String, Object> update(User user) {
        HashMap<String, Object> map=new HashMap<String, Object>();
        userServiceWS.update(user);
        map.put("success",true);
        return map;
    }
    //查询已支付/未支付/待评价订单
    //查询用户所有订单
}
