package com.baizhi.fifth.entity;

import java.io.Serializable;

/**
 * 订单项
 */
public class OrderItem implements Serializable {
    private String id;//主键
    private Integer count;//数量
    private Double sub;//小结
    //关系维护
    private Order order;//订单
    private Product product;//商品

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", count=" + count +
                ", sub=" + sub +
                ", order=" + order +
                ", product=" + product +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderItem() {

    }

    public OrderItem(String id, Integer count, Double sub, Order order, Product product) {

        this.id = id;
        this.count = count;
        this.sub = sub;
        this.order = order;
        this.product = product;
    }
}
