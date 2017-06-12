package com.baizhi.fifth.entity;

import java.io.Serializable;

/**
 * 药品图片表
 */
public class Picture implements Serializable{
    private String id;//主键
    private String imagePath;//图片路径
    private String imageType;//图片类型

    //set/get方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    //构造方法
    public Picture() {
    }

    public Picture(String id, String imagePath, String imageType) {
        this.id = id;
        this.imagePath = imagePath;
        this.imageType = imageType;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", imageType='" + imageType + '\'' +
                '}';
    }
}
