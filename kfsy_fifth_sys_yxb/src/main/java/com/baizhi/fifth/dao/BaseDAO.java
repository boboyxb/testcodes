package com.baizhi.fifth.dao;

import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public interface BaseDAO<T> {
    //增删改
    void delete(String id);
    void update(T t);
    void insert(T t);
    //查
    T queryOne(String id);
    List<T> queryAll();
}
