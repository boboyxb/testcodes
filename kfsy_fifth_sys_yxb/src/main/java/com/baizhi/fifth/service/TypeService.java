package com.baizhi.fifth.service;

import com.baizhi.fifth.entity.Type;
import com.github.pagehelper.Page;

public interface TypeService {
    //查询所有分类
    Page<Type> queryAll(Integer rows, Integer page);
    //删除
    void delete(String id);
    //添加
    void insert(Type type);
}
