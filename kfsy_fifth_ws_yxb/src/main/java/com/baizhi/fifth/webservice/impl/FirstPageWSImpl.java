package com.baizhi.fifth.webservice.impl;

import com.baizhi.fifth.entity.Alternate;
import com.baizhi.fifth.entity.Product;
import com.baizhi.fifth.entity.Type;
import com.baizhi.fifth.service.impl.FirstPageServiceWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebService;
import javax.ws.rs.*;
import java.util.ArrayList;

@Controller
@RequestMapping("/firstPage")
public class FirstPageWSImpl{
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FirstPageServiceWS firstPageServiceWS;

    @RequestMapping(value="/alternate/",method = RequestMethod.GET)
    @ResponseBody
    //查询轮播图
    public ArrayList<Alternate> queryAllAlternate(){
        ArrayList<Alternate> list;
        //从缓存中拿轮播图
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object alternate = opsForValue.get("alternate");
        //如果轮播图为空
        if(alternate==null){
            //查询,放入redis
            list= (ArrayList<Alternate>) firstPageServiceWS.queryAllAlternate();
            opsForValue.set("alternate",list);
        }else{
            list= (ArrayList<Alternate>) alternate;
        }
        return list;
    }
    @RequestMapping(value="/type/",method = RequestMethod.GET)
    @ResponseBody
    //查询首页所有分类
    public ArrayList<Type> queryAllType(){
        ArrayList<Type> list;
        //从缓存中拿种类
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object types = opsForValue.get("types");
        //如果种类为空
        if(types==null){
            //查询,放入redis
            list= (ArrayList<Type>) firstPageServiceWS.queryAllType();
            opsForValue.set("types",list);
        }else{
            list= (ArrayList<Type>) types;
        }
        return list;
    }
    @RequestMapping(value="/type/{typeId}/",method = RequestMethod.GET)
    @ResponseBody
    //查询某个类别下的全部商品           每次只加载8条,用户每下滑一次就是查询往后的八条商品
    public ArrayList<Product> queryProductsByType(@PathVariable("typeId")String typeId){
        ArrayList<Product> products = (ArrayList<Product>) firstPageServiceWS.queryProductsByType(typeId);
        return products;
    }
    @RequestMapping(value="/type/{typeId}/{lorder}/",method = RequestMethod.GET)
    @ResponseBody
    //根据价格排序某一类别下的全部商品
    public ArrayList<Product> queryProductsTypeAndOrder(@PathVariable("typeId")String typeId,@PathVariable("lorder")String lorder){
        ArrayList<Product> products = (ArrayList<Product>) firstPageServiceWS.queryProductsTypeAndOrder(typeId, lorder);
        return products;
    }
    @RequestMapping(value="/product/{productId}/",method = RequestMethod.GET)
    @ResponseBody
    //根据id查询药品
    public Product queryOneProduct(@PathVariable("productId")String id){
        Product product = firstPageServiceWS.queryOneProduct(id);
        return product;
    }
}
