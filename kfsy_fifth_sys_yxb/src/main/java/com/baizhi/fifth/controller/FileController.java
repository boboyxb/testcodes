package com.baizhi.fifth.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * 文件上传
     */
    @RequestMapping("/upload")
    public String test(MultipartFile typeIMG, HttpServletRequest request) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //获取父路径
        String parent = new File(realPath).getParent();
        File file = new File(parent,"upload");
        if(!file.exists()){
            file.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(typeIMG.getOriginalFilename());
        //上传文件
        typeIMG.transferTo(new File(file,newFileName));
        return "index";
    }
    /**
     * 文件下载
     */
    @RequestMapping("/download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取目录
        String webapps = new File(request.getSession().getServletContext().getRealPath("/")).getParent();
        //获取上传文件的输入流
        FileInputStream fis = new FileInputStream(new File(webapps + "/upload", fileName));
        //获取响应流
        ServletOutputStream os = response.getOutputStream();
        //设置下载的响应类型类型
        response.setContentType("text/plain");
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"UTF-8"));
        //从输入流读入缓存
        int len = 0;
        byte[] b = new byte[1024];

        while(true){
            len = fis.read(b);
            if(len==-1)break;
            os.write(b,0, len);
        }
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);
    }
}
