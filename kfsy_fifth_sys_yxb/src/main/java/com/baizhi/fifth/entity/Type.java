package com.baizhi.fifth.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 类别表
 */

public class Type implements Serializable {
    private String id;//主键
    private String name;//姓名
    private String href;
    private List<Product> products;

    public Type(String id, String name, String href, List<Product> products) {
        this.id = id;
        this.name = name;
        this.href = href;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", products=" + products +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Type() {

    }
}
