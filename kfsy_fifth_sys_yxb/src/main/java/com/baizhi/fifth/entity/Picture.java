package com.baizhi.fifth.entity;

import java.io.Serializable;

/**
 * 药品图片表
 */
public class Picture implements Serializable{
    private String id;//主键
    private String imagePath;//图片路径
    private String imageType;//图片类型

    private Medicine medicine;
    //set/get方法

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

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

    public Picture(String id, String imagePath, String imageType, Medicine medicine) {
        this.id = id;
        this.imagePath = imagePath;
        this.imageType = imageType;
        this.medicine = medicine;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id='" + id + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", imageType='" + imageType + '\'' +
                ", medicine=" + medicine +
                '}';
    }
}
