package com.baizhi.fifth.entity;

import java.io.Serializable;


public class Admin implements Serializable {
    //定义私有属性
    private String id;//管理员主键
    private String name;//管理员姓名
    private String password;//管理员登入密码
    private String salt;//私盐
    private Integer level;//管理员的级别

    //提供公开的set、get方法

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    //有参、无参构造 重构toString方法

    public Admin() {
    }

    public Admin(String id, String name, String password, String salt, Integer level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.level = level;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", level=" + level +
                '}';
    }
}
