package com.baizhi.fifth.service;


import com.baizhi.fifth.entity.Admin;
import com.github.pagehelper.Page;

import java.util.List;

public interface AdminService {
    //增删改查
    void deleteAdmin(String id);
    void insertAdmin(Admin admin);
    void updateAdmin(Admin admin);
    Page<Admin> queryAllAdmin(Integer rows, Integer page);
    Admin queryOneAdmin(String id);
    Admin queryByName(String name);
    //登陆
    Admin loginforAdmin(Admin admin);
}
