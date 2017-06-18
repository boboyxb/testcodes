package com.baizhi.fifth.service;

import com.baizhi.fifth.entity.User;
import com.github.pagehelper.Page;

import java.util.Date;
import java.util.List;

public interface UserService {
    //模糊查询
    Page<User> queryLike(String type,String content,Integer rows, Integer page);
    //查询所有用户
    Page<User> queryAll(Integer rows, Integer page);
    //查询一个用户
    User queryOne(String id);
    //删除一个用户
    void deleteOne(String id);
    //查询所有用户  不分页
    List<User> queryAllUsers();
    //查询在某时间到某一时间的注册人数
    Integer countRegist(Date begin, Date end);
}
