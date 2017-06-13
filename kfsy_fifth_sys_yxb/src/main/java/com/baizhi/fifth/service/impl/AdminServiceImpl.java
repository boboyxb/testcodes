package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.AddressDAO;
import com.baizhi.fifth.dao.AdminDAO;
import com.baizhi.fifth.entity.Admin;
import com.baizhi.fifth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.MD5Utils;
import util.SaltUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    //注入dao对象
    @Autowired
    private AdminDAO adminDAO;


    /**
     * 根据id删除管理员
     * @param id
     */
    public void deleteAdmin(String id) {
        adminDAO.delete(id);
    }

    /**
     * 添加一位管理员
     * @param admin
     */
    public void insertAdmin(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        String salt = SaltUtils.getSalt(4);
        admin.setSalt(salt);
        admin.setPassword(MD5Utils.getMd5Code(admin.getPassword() + salt));
        adminDAO.insert(admin);
    }

    /**
     * 更新一位管理员
     * @param admin
     */
    public void updateAdmin(Admin admin) {
        adminDAO.update(admin);
    }

    /**
     * 查询所有管理员
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public List<Admin> queryAllAdmin() {
        List<Admin> list = adminDAO.queryAll();
        return list;
    }

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Admin queryOneAdmin(String id) {
        Admin admin = adminDAO.queryOne(id);
        return admin;
    }

    /**
     * 根据名字查询管理员
     * @param name
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)
    public Admin queryByName(String name) {
        Admin admin = adminDAO.queryByName(name);
        return admin;
    }

    public Admin loginforAdmin(Admin admin) {
        //根据用户名查询
        Admin adminDB = adminDAO.queryByName(admin.getName());
        if(adminDB==null){
            throw new RuntimeException("该用户不存在");
        }
        //比较用户的密码
        String salt=adminDB.getSalt();
        String pwd=MD5Utils.getMd5Code(admin.getPassword() + salt);
        if(!pwd.equals(adminDB.getPassword())){
            throw new RuntimeException("密码不正确");
        }
        return adminDB;
    }
}
