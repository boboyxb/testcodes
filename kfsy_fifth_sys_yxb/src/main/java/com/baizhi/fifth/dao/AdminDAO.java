package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Admin;

import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public interface AdminDAO extends BaseDAO<Admin> {
    //根据名字查
    Admin queryByName(String name);
    //根据级别查
    List<Admin> queryByLevel(Integer level);
}
