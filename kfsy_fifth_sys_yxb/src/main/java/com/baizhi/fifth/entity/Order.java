package com.baizhi.fifth.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
public class Order implements Serializable {
    private String id;
    private String name;
    private String elseMessage;
    private String status;
    private Date createTime;
    private Double total;
    //关系属性
    private Address address;
    private User user;
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(String id, String name, String elseMessage, String status, Date createTime, Double total, Address address, User user) {
        this.id = id;
        this.name = name;
        this.elseMessage = elseMessage;
        this.status = status;
        this.createTime = createTime;
        this.total = total;
        this.address = address;
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
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

    public String getElseMessage() {
        return elseMessage;
    }

    public void setElseMessage(String elseMessage) {
        this.elseMessage = elseMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", elseMessage='" + elseMessage + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", total=" + total +
                ", address=" + address +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }
}
