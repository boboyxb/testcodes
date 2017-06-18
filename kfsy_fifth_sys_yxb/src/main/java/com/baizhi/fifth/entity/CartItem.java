package com.baizhi.fifth.entity;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Integer count;//数量
    private Double sub;//小结
    private String status;//状态---代表该购物项是否被选中
    //关系维护
    private Product product;//商品

    public CartItem() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSub() {
        return sub;
    }

    public void setSub(Double sub) {
        this.sub = sub;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartItem(Integer count, Double sub, String status, Product product) {

        this.count = count;
        this.sub = sub;
        this.status = status;
        this.product = product;
    }
}
