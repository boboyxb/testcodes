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
    private Medicine medicine;//药品
    //get、set方法
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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
    //构造方法

    public OrderItem() {
    }

    public OrderItem(String id, Integer count, Double sub, Order order, Medicine medicine) {
        this.id = id;
        this.count = count;
        this.sub = sub;
        this.order = order;
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id='" + id + '\'' +
                ", count=" + count +
                ", sub=" + sub +
                '}';
    }
}
