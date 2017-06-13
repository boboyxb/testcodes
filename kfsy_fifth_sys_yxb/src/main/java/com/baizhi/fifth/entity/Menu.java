package com.baizhi.fifth.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {
    private String id;
    private String name;
    private String iconCls;
    private String href;
    //关系属性
    private List<Menu> menus;

    public Menu() {
    }

    public Menu(String id, String name, String iconCls, String href, List<Menu> menus) {
        this.id = id;
        this.name = name;
        this.iconCls = iconCls;
        this.href = href;
        this.menus = menus;
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
