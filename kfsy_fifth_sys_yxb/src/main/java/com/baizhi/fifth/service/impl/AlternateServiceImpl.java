package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.AlternateDAO;
import com.baizhi.fifth.entity.Alternate;
import com.baizhi.fifth.service.AlternateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class AlternateServiceImpl implements AlternateService {
    @Autowired
    private AlternateDAO alternateDAO;
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Alternate> queryAll(Integer rows, Integer page) {
        Page<Alternate> pages= PageHelper.startPage(page,rows);
        alternateDAO.queryAll();
        return pages;
    }

    public void delete(String id) {
        alternateDAO.delete(id);
    }

    public void insert(Alternate alternate) {
        alternate.setId(UUID.randomUUID().toString());
        alternateDAO.insert(alternate);
    }
}
