package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Type;
import com.baizhi.fifth.service.TypeService;
import com.github.pagehelper.Page;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Resource
    private TypeService typeService;
    //查询所有分类
    @RequestMapping("/queryAllType")
    @ResponseBody
    public HashMap<String,Object> queryAllType(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Type> types = typeService.queryAll(rows, page);
        map.put("rows",types.getResult());
        map.put("total",types.getTotal());
        return map;
    }
    //查询所有分类
    @RequestMapping("/queryAllTypes")
    @ResponseBody
    public List<Type> queryAllTypes(){
        List<Type> types = typeService.queryAllTypes();
        return types;
    }
    //删除分类
    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<String,Object> delete(String id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            typeService.delete(id);
        } catch (Exception e) {
            map.put("success",false);
            map.put("message","暂时无法删除,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }
    //添加分类
    @RequestMapping("/insert")
    @ResponseBody
    public HashMap<String,Object> insert(Type type){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            typeService.insert(type);
        } catch (Exception e) {
            map.put("success",false);
            System.out.println(e.getMessage());
            map.put("message","暂时无法添加,请稍后操作");
            return map;
        }
        map.put("success",true);
        return map;
    }
    //上传文件并把路径返回到页面
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile typeIMG, HttpServletRequest request) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //在当前项目下创建路径
        File file = new File(realPath,"upload/types");
        if(!file.exists()){
            file.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(typeIMG.getOriginalFilename());
        //上传文件
        typeIMG.transferTo(new File(file,newFileName));
        return "/upload/types/"+newFileName;
    }

}
