package com.baizhi.fifth.service;


import com.baizhi.fifth.entity.Alternate;
import com.github.pagehelper.Page;

public interface AlternateService {
    //查询所有轮播图
    Page<Alternate> queryAll(Integer rows, Integer page);
    //删除
    void delete(String id);
    //添加
    void insert(Alternate alternate);
}
