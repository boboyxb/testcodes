package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Type;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface TypeDAO extends BaseDAO<Type> {
    //查询带商品的分类
    Type queryTypeWithProducts(String id);
    //查询价格排序的分类下的s商品
    Type queryProductsTypeAndOrder(@Param("id") String id,@Param("lorder") String lorder);
}
