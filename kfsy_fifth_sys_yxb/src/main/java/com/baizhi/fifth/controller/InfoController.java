package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Info;
import com.baizhi.fifth.service.InfoService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("/info")
public class InfoController {
    @Resource
    private InfoService infoService;

    /**
     * 查询所有评价
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public HashMap<String,Object> queryAll(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Info> adminPage = infoService.queryAll(rows, page);
        map.put("rows",adminPage.getResult());
        map.put("total",adminPage.getTotal());
        return map;
    }
    /**
     * 模糊查询
     */
    @RequestMapping("/queryLike")
    @ResponseBody
    public HashMap<String,Object> queryLike(String type,String content,Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Info> userPage = infoService.queryLike(type,content,rows,page);
        map.put("rows",userPage.getResult());
        map.put("total",userPage.getTotal());
        return map;
    }
}
