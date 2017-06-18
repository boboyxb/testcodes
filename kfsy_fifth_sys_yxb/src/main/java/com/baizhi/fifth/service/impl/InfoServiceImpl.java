package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.InfoDAO;
import com.baizhi.fifth.entity.Info;
import com.baizhi.fifth.service.InfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoDAO infoDAO;
    private Page<Info> pages;

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Info> queryAll(Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        infoDAO.queryAll();
        return pages;
    }

    public Page<Info> queryLike(String type, String content, Integer rows, Integer page) {
        pages= PageHelper.startPage(page,rows);
        infoDAO.queryLike(type,content);
        return pages;
    }
}
