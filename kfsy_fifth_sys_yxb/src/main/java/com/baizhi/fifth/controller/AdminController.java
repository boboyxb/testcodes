package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Admin;
import com.baizhi.fifth.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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

}
