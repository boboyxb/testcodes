package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.entity.Admin;
import com.baizhi.fifth.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @org.junit.Test
    public void deleteAdmin() throws Exception {

    }

    @org.junit.Test
    public void insertAdmin() throws Exception {
        adminService.insertAdmin(new Admin("001","yuxb","123456",null,2));
    }

    @org.junit.Test
    public void updateAdmin() throws Exception {

    }

    @org.junit.Test
    public void queryAllAdmin() throws Exception {

    }

    @org.junit.Test
    public void queryOneAdmin() throws Exception {

    }

    @org.junit.Test
    public void queryByName() throws Exception {
        Admin yuxb = adminService.queryByName("yuxb");
        System.out.println(yuxb);
    }
    @Test
    public void testLogin(){
        Admin yuxb = adminService.loginforAdmin(new Admin(null, "yuxb", "122456", null, null));
        System.out.println(yuxb);
    }

}