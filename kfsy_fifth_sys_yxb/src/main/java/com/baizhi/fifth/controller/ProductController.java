package com.baizhi.fifth.controller;

import com.baizhi.fifth.entity.Medicine;
import com.baizhi.fifth.entity.Picture;
import com.baizhi.fifth.entity.Product;
import com.baizhi.fifth.service.ProductService;
import com.github.pagehelper.Page;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 查询所有药品
     */
    @RequestMapping("/queryAll")
    @ResponseBody
    public HashMap<String,Object> queryAll(Integer rows, Integer page){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Product> productPage = productService.queryAll(rows, page);
        map.put("rows",productPage.getResult());
        map.put("total",productPage.getTotal());
        return map;
    }
    /**
     * 模糊查询所有药品
     */
    @RequestMapping("/queryLikeName")
    @ResponseBody
    public HashMap<String,Object> queryLikeName(Integer rows, Integer page,String name){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page<Product> productPage = productService.queryLikeName(rows,page,name);
        map.put("rows",productPage.getResult());
        map.put("total",productPage.getTotal());
        return map;
    }
    /**
     * 查询一个
     */
    @RequestMapping("/queryOne")
    @ResponseBody
    public Product queryOne(String id){
        Product product = productService.queryOne(id);
        return product;
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public HashMap<String,Object> delete(String id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            productService.delete(id);
        } catch (Exception e) {
            map.put("message","暂时无法删除,请稍后操作");
            map.put("success",false);
            return map;
        }
        map.put("success",true);
        return map;
    }
    /**
     * 更新
     */
    @RequestMapping("/update")
    @ResponseBody
    public HashMap<String,Object> update(Product product){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            productService.update(product);
        } catch (Exception e) {
            map.put("message","暂时无法更新,请稍后操作");
            map.put("success",false);
            return map;
        }
        map.put("success",true);
        return map;
    }
    /**
     * 修改库存
     */
    @RequestMapping("/alterStock")
    @ResponseBody
    public HashMap<String,Object> alterStock(Product product){
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            productService.alterStock(product);
        } catch (Exception e) {
            map.put("message","暂时无法更新,请稍后操作");
            map.put("success",false);
            return map;
        }
        map.put("success",true);
        return map;
    }

    /**
     * 添加
     */
    @RequestMapping("/insert")
    @ResponseBody
    public HashMap<String,Object> insert(Product product,String paths){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Picture> pictures=new ArrayList<Picture>();
        String[] ss = paths.split(";");
        for (String s : ss) {
            Picture p = new Picture();
            p.setImagePath(s);
            pictures.add(p);
        }
        product.getMedicine().setPictures(pictures);
        try {
            productService.insert(product);
        } catch (Exception e) {
            map.put("message","暂时无法添加,请稍后操作");
            System.out.println(e.getMessage());
            map.put("success",false);
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
        File file = new File(realPath,"upload/product");
        if(!file.exists()){
            file.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(typeIMG.getOriginalFilename());
        //上传文件
        typeIMG.transferTo(new File(file,newFileName));
        return "/upload/product/"+newFileName;
    }
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(HttpServletRequest request) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath,"upload/product");
        if(!file.exists()){
            file.mkdirs();
        }
        MultipartHttpServletRequest multyfiles= (MultipartHttpServletRequest) request;
        List<MultipartFile> gs = multyfiles.getFiles("typeIMGs");
        StringBuilder sb=new StringBuilder();
        for (MultipartFile g : gs) {
            String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(g.getOriginalFilename());
            //上传文件
            g.transferTo(new File(file,newFileName));
            sb.append("/upload/product/"+newFileName+";");
        }
        return sb.toString();
    }
}
