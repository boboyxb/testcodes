package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.MenuDAO;
import com.baizhi.fifth.entity.Menu;
import com.baizhi.fifth.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuDAO menuDAO;
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public List<Menu> queryMainMenu() {
        List<Menu> menus = menuDAO.queryMainMenu();
        return menus;
    }
}
