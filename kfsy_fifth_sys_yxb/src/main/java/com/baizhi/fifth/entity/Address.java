package com.baizhi.fifth.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/11.
 */
public class Address implements Serializable {
    //定义私有属性
    private String id;//地址主键
    private String recevieAddress;//收货地址
    private String detailAddress;//详细地址
    private String personName;//收货人姓名
    private String phone;//联系电话
    private User user;//用户

    //get、set方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecevieAddress() {
        return recevieAddress;
    }

    public void setRecevieAddress(String recevieAddress) {
        this.recevieAddress = recevieAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //有参 、无参构造 一级toString方法
    public Address() {
    }

    public Address(String id, String recevieAddress, String detailAddress, String personName, String phone) {
        this.id = id;
        this.recevieAddress = recevieAddress;
        this.detailAddress = detailAddress;
        this.personName = personName;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", recevieAddress='" + recevieAddress + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", personName='" + personName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
