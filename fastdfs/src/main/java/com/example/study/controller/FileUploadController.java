package com.example.study.controller;

import com.example.study.config.FastDFSClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {
    /**
     * 文件上传
     */
    @PostMapping("/uploadFile")
    public String upload(MultipartFile multipartFile) throws Exception {


        String[] upload = FastDFSClient.upload(multipartFile);
        //拼接访问地址
        String url="http://192.168.32.209/"+upload[0]+"/"+upload[1];
        return url;
    }
}