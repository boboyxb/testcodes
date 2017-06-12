package com.baizhi.fifth.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/11.
 */
public class Product implements Serializable{
    //定义私有属性
    private String id;//商品主键
    private String name;//商品名称
    private String indication;//功能主治
    private String standard;//规格
    private Double price;//价格
    private String iamgePath;//商品缩略图地址
    private Integer stock;//库存

    private Type type;//商品所属类型
    private Medicine medicine;//商品对应的药品详细详细
}
