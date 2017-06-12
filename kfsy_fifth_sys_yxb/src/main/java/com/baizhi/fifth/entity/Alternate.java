package com.baizhi.fifth.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/11.
 */
public class Alternate  implements Serializable {
    //定义私有属性
    private String id;
    private String imagePath;

    @Override
    public String toString() {
        return "Alternate{" +
                "id='" + id + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
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

    public Alternate(String id, String imagePath) {

        this.id = id;
        this.imagePath = imagePath;
    }

    public Alternate() {

    }
}
