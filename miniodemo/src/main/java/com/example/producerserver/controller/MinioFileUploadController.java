package com.example.producerserver.controller;

import com.example.producerserver.util.MinioUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Log4j2
public class MinioFileUploadController {
    @Autowired
    private MinioUtils minioUtils;

    /**
     * @param file     文件
     * @param fileName 文件名称
     * @return {@link AjaxResult }
     * @Description 上传文件
     * @Author  zy
     * @Date 2024/08/01
     */
    @GetMapping("/upload")
    public Object uploadFile(@RequestParam("file") MultipartFile file, String fileName) {
        StopWatch stopWatch=new StopWatch();
        stopWatch.start("测试上传");
        minioUtils.upload(file, fileName);
        stopWatch.stop();
       log.info(stopWatch.prettyPrint());
        return"上传成功";

    }

    /**
     * @param fileName 文件名称
     * @return {@link ResponseEntity }
     * @Description dowload文件
     * @Author  zy
     * @Date 2024/08/01
     */
    @GetMapping("/dowload")
    public ResponseEntity dowloadFile(@RequestParam("fileName") String fileName) {
        return minioUtils.download(fileName);
    }

    /**
     * @param fileName 文件名称
     * @return {@link AjaxResult }
     * @Description 得到文件url
     * @Author  zy
     * @Date 2024/08/01
     */
    @GetMapping("/getUrl")
    public Object getFileUrl(@RequestParam("fileName") String fileName){
        HashMap map=new HashMap();
        map.put("FileUrl",minioUtils.getFileUrl(fileName));
        return map;
    }
}