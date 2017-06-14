package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Admin;
import com.baizhi.fifth.service.AdminService;
import com.github.pagehelper.Page;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private AdminService adminService;

    /**
     * 登陆
     */
    @RequestMapping("/login")
    @ResponseBody
    public HashMap<String,Object> login(Admin admin, HttpSession session){
        Admin adminDB =null;
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            adminDB = adminService.loginforAdmin(admin);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message", e.getMessage());
            return map;
        }
        session.setAttribute("admin",adminDB);
        map.put("success",true);
        return map;
    }
    /**
     * 查询所有管理员
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public HashMap<String,Object> queryAll(Integer rows,Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Admin> adminPage = adminService.queryAllAdmin(rows, page);
        map.put("rows",adminPage.getResult());
        map.put("total",adminPage.getTotal());
        return map;
    }
    /**
     * 删除管理员
     */
    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<String,Object> delete(String id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            adminService.deleteAdmin(id);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","暂时无法删除,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }
    /**
     * 查询一个管理员
     */
    @RequestMapping("/queryById")
    @ResponseBody
    public Admin queryById(String id){
        Admin admin = adminService.queryOneAdmin(id);
        return admin;
    }
    /**
     * 更新一个
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<String,Object> update(Admin admin){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            adminService.updateAdmin(admin);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","暂时无法修改,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }
    /**
     * 添加一个管理员
     */
    @RequestMapping("/insert")
    @ResponseBody
    public HashMap<String,Object> insert(Admin admin){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            adminService.insertAdmin(admin);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","暂时无法添加,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }
}
