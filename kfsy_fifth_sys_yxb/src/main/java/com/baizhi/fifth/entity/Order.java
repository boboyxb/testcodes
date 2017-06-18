package com.baizhi.fifth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private String id;
    private String num;
    private String elseMessage;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format ="yyyy-MM-dd",name="createTime")
    private Date createTime;
    private Double total;
    //关系属性
    private Address address;
    private User user;
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(String id, String num, String elseMessage, String status, Date createTime, Double total, Address address, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.num = num;
        this.elseMessage = elseMessage;
        this.status = status;
        this.createTime = createTime;
        this.total = total;
        this.address = address;
        this.user = user;
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", num='" + num + '\'' +
                ", elseMessage='" + elseMessage + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", total=" + total +
                ", address=" + address +
                ", user=" + user +
                ", orderItems=" + orderItems +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
