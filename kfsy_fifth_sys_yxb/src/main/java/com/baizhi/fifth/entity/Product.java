package com.baizhi.fifth.entity;

import java.io.Serializable;

public class Product implements Serializable{
    //定义私有属性
    private String id;//商品主键
    private String name;//商品名称
    private String indication;//功能主治
    private String standard;//规格
    private Double price;//价格
    private String imagePath;//商品缩略图地址
    private Integer stock;//库存

    private Type type;//商品所属类型
    private Medicine medicine;//商品对应的药品详细详细

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", indication='" + indication + '\'' +
                ", standard='" + standard + '\'' +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                ", stock=" + stock +
                ", type=" + type +
                ", medicine=" + medicine +
                '}';
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

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Product(String id, String name, String indication, String standard, Double price, String imagePath, Integer stock, Type type, Medicine medicine) {

        this.id = id;
        this.name = name;
        this.indication = indication;
        this.standard = standard;
        this.price = price;
        this.imagePath = imagePath;
        this.stock = stock;
        this.type = type;
        this.medicine = medicine;
    }

    public Product() {

    }
}
