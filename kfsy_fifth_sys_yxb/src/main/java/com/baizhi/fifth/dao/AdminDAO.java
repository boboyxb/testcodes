package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Admin;

import java.util.List;


public interface AdminDAO extends BaseDAO<Admin> {
    //根据名字查
    Admin queryByName(String name);
}
