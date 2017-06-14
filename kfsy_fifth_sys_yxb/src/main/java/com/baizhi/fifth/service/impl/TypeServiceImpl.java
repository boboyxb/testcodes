package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.TypeDAO;
import com.baizhi.fifth.entity.Type;
import com.baizhi.fifth.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDAO typeDAO;

    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Page<Type> queryAll(Integer rows, Integer page) {
        Page<Type> pages= PageHelper.startPage(page,rows);
        typeDAO.queryAll();
        return pages;
    }

    public void delete(String id) {
        typeDAO.delete(id);
    }

    public void insert(Type type) {
        type.setId(UUID.randomUUID().toString());
        typeDAO.insert(type);
    }
}
