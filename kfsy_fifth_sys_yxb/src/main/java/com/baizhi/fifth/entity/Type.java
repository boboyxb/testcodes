package com.baizhi.fifth.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 类别表
 */

public class Type implements Serializable {
    private String id;//主键
    private String name;//姓名

    private List<Product> products;

    //set/get方法


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //构造方法
    public Type() {
    }

    public Type(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
