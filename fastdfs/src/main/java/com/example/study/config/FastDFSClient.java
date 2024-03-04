package com.example.study.config;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class FastDFSClient {
    @Value("${fastdfs.tracker_server}")
    private String trackerServerIp;


    private static TrackerClient trackerClient;
    //加载文件
    static {
        try {

            //加载fasetdfs配置项
            Properties props = new Properties();
            props.put(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"ip:22122");
            props.put(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS,3);
            props.put(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS,30);

            ClientGlobal.initByProperties(props);
            trackerClient=new TrackerClient();
        } catch (Exception e) {

        }
    }



    /**
     * 文件上传
     */
    public static String[]  upload(MultipartFile file) throws Exception {

        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        StorageClient1 storageClient1 = null;
        trackerServer = trackerClient.getTrackerServer();
        if (trackerServer == null) {
           // logger.error("getConnection return null");
        }
        storageServer = trackerClient.getStoreStorage(trackerServer);
        storageClient1 = new StorageClient1(trackerServer, storageServer);
        String[] uploads = init().upload_file(file.getBytes(),file.getOriginalFilename(), null);
        return uploads;
    }

    public static void deleteFile(String fileUrl) throws Exception {
        init().delete_file1(fileUrl);
    }
}
