package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.User;
import com.baizhi.fifth.entity.UserPOI;
import com.baizhi.fifth.service.UserService;
import com.baizhi.kfsy.util.EncodeUtils;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 查询所有用户
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public HashMap<String,Object> queryAll(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<User> userPage = userService.queryAll(rows, page);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }
    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<String,Object> delete(String id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            userService.deleteOne(id);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","暂时无法删除,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }
    /**
     * 查询一个用户
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public User queryById(String id){
        User user= userService.queryOne(id);
        return user;
    }
    /**
     * 模糊查询
     */
    @RequestMapping("/queryLike")
    @ResponseBody
    public HashMap<String,Object> queryLike(String type,String content,Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<User> userPage = userService.queryLike(type,content,rows,page);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }

    /**
     * 下载用户
     */
    @RequestMapping("/down")
    public void down(HttpServletResponse response) throws Exception{
        //获取响应输出流
        ServletOutputStream os = response.getOutputStream();
		//设置响应类型
		response.setContentType("txt/plain");
        List<User> all = userService.queryAllUsers();
        //调用方法,把学生信息以写到输出流里
        UserPOI p=new UserPOI();
        p.download(os,all);
        //设置响应头
        response.setHeader("content-disposition", "attachment;fileName="+ EncodeUtils.getEncoded("all.xls"));
        os.close();
    }
    /**
     * 查询最近几个月的注册数量
     */
    @RequestMapping("/countRegistUser")
    @ResponseBody
    public Map<String,Object> countRegistUser(){
        Map<String, Object> map = new HashMap<String, Object>();
        //注册事件
        //  日历
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        System.out.println("系统时间:"+calendar.getTime());
        calendar.add(Calendar.DATE,-1);
        Integer oneday = userService.countRegist(calendar.getTime(), date);
        calendar.add(Calendar.DATE,-1);
        Integer twoday = userService.countRegist(calendar.getTime(), date);
        calendar.add(Calendar.DATE,-5);
        Integer week = userService.countRegist(calendar.getTime(), date);
        calendar.add(Calendar.MONTH,-1);
        Integer mounth = userService.countRegist(calendar.getTime(), date);
        calendar.add(Calendar.YEAR,-1);
        Integer year = userService.countRegist(calendar.getTime(), date);
        map.put("dates", Arrays.asList("一天内","两天内","一周内","一个月内","一年"));
        map.put("counts",Arrays.asList(oneday,twoday,week,mounth,year));
        return map;
    }
}
