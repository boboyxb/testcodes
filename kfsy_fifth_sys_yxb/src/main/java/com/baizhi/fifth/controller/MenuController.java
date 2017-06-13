package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Menu;
import com.baizhi.fifth.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;
    @RequestMapping("/queryMenus")
    @ResponseBody
    public List<Menu> queryMenus(){
        List<Menu> menus = menuService.queryMainMenu();
        return menus;
    }
}
