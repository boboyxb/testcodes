package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Product;

import java.util.List;

public interface ProductDAO extends BaseDAO<Product> {
    //模糊查询药品
    List<Product> queryLikeName(String name);
    //修改库存
    void alterStock(Product product);
    //根据分类查询商品
    List<Product> queryByType(String typeId);
}
