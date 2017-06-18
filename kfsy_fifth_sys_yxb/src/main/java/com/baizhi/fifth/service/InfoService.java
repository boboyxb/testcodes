package com.baizhi.fifth.service;


import com.baizhi.fifth.entity.Info;
import com.github.pagehelper.Page;

public interface InfoService {
    //查询所有评价
    Page<Info> queryAll(Integer rows, Integer page);
    //模糊查询
    Page<Info> queryLike(String type,String content,Integer rows, Integer page);
}
