package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.AlternateDAO;
import com.baizhi.fifth.dao.ProductDAO;
import com.baizhi.fifth.dao.TypeDAO;
import com.baizhi.fifth.entity.Alternate;
import com.baizhi.fifth.entity.Product;
import com.baizhi.fifth.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("firstPageServiceWS")
public class FirstPageServiceWS {
    @Autowired
    private AlternateDAO alternateDAO;
    @Autowired
    private TypeDAO typeDAO;
    @Autowired
    private ProductDAO productDAO;
    //展示限时抢购商品
    //查询轮播图
    public List<Alternate> queryAllAlternate(){
        List<Alternate> alternates = alternateDAO.queryAll();
        return alternates;
    }
    //查询首页所有分类
    public List<Type> queryAllType(){
        List<Type> types = typeDAO.queryAll();
        return types;
    }
    //查询某个类别下的全部商品           每次只加载8条,用户每下滑一次就是查询往后的八条商品
    public List<Product> queryProductsByType(String typeId){
        Type type = typeDAO.queryTypeWithProducts(typeId);
        return type.getProducts();
    }
    //根据价格排序某一类别下的全部商品
    public List<Product> queryProductsTypeAndOrder(String typeId,String lorder){
        Type type = typeDAO.queryProductsTypeAndOrder(typeId, lorder);
        return type.getProducts();
    }
    //根据id查询药品
    public Product queryOneProduct(String id){
        Product product = productDAO.queryOne(id);
        return product;
    }
}
