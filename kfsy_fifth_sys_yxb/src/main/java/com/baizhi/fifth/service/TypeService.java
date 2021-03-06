package com.baizhi.fifth.service;

import com.baizhi.fifth.entity.Type;
import com.github.pagehelper.Page;

import java.util.List;

public interface TypeService {
    //查询所有分类
    Page<Type> queryAll(Integer rows, Integer page);
    //删除
    void delete(String id);
    //添加
    void insert(Type type);
    //查询所有种类
    List<Type> queryAllTypes();
}
