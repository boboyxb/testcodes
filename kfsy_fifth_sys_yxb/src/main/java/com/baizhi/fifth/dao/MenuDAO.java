package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Menu;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */
public interface MenuDAO extends  BaseDAO<Menu> {
    //查询所有1级菜单   将2级菜单封装到1级菜单里
    List<Menu> queryMainMenu();
}
