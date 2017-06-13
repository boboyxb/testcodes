package com.baizhi.fifth.service;

import com.baizhi.fifth.entity.Menu;

import java.util.List;

public interface MenuService {
    //查询所有1级菜单   将2级菜单封装到1级菜单里
    List<Menu> queryMainMenu();
}
