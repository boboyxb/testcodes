package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Product;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface ProductDAO extends BaseDAO<Product> {
    //模糊查询药品
    List<Product> queryLikeName(String name);
}
