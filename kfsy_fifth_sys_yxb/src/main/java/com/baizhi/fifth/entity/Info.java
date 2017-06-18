package com.baizhi.fifth.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Info  implements Serializable {
    //定义私有属性
    private String id;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format ="yyyy-MM-dd",name="createTime")
    private Date createTime;
    private String content;

    private User user;
    private Order order;

    //提供公开的get、set方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    //有参、无参构造  重写toString方法

    public Info() {
    }

    public Info(String id, Integer score, Date createTime, String content) {
        this.id = id;
        this.score = score;
        this.createTime = createTime;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", score=" + score +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
