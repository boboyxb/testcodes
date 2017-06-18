package com.baizhi.fifth.service;


import com.baizhi.fifth.entity.Product;
import com.github.pagehelper.Page;

public interface ProductService {
    //查询一个
    Product queryOne(String id);
    //模糊查询
    Page<Product> queryLikeName(Integer rows, Integer page,String name);
    //查询所有
    Page<Product> queryAll(Integer rows, Integer page);
    //删除
    void delete(String id);
    //添加
    void insert(Product product);
    //修改
    void update(Product product);
    //修改一个商品的库存
    void alterStock(Product product);
}
