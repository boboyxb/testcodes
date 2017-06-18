package com.baizhi.fifth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 */
public class User implements Serializable {
    //用户属性
    @ToExcel("主键")
    private String id;//主键
    @ToExcel("姓名")
    private String name;//名称
    @ToExcel("手机号")
    private String phone;//手机号
    @ToExcel("密码")
    private String password;//密码
    @ToExcel("私盐")
    private String salt;//私盐
    @ToExcel("注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format ="yyyy-MM-dd",name="registDate")
    private Date registDate;//创建时间
    @ToExcel("注册状态")
    private String status;//状态
    //关系维护
    @ToExcel("地址")
    private List<Address> addresses;
    @ToExcel("评价")
    private List<Info> infos;
    @ToExcel("订单")
    private List<Order> orders;
    //set/get方法

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    //构造方法


    public User() {
    }

    public User(String id, String name, String phone, String password, String salt, Date registDate, String status, List<Address> addresses, List<Info> infos, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.registDate = registDate;
        this.status = status;
        this.addresses = addresses;
        this.infos = infos;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", registDate=" + registDate +
                ", status='" + status + '\'' +
                '}';
    }
}
