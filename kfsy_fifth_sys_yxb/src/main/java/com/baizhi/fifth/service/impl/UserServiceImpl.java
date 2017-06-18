package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.UserDAO;
import com.baizhi.fifth.entity.User;
import com.baizhi.fifth.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    //用page分页插件查询管理员
    private Page<User> pages;


    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<User> queryLike(String type,String content,Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        userDAO.queryLike(type,content);
        return pages;
    }
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<User> queryAll(Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        userDAO.queryAll();
        return pages;
    }
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public User queryOne(String id) {
        User user = userDAO.queryOne(id);
        return user;
    }

    public void deleteOne(String id) {
        userDAO.delete(id);
    }

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public List<User> queryAllUsers() {
        List<User> users = userDAO.queryAll();
        return users;
    }

    public Integer countRegist(Date begin, Date end) {
        Integer count = userDAO.countRegist(begin, end);
        return count;
    }
}
