package com.example.study.util;

import com.sun.javafx.collections.MappingChange;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HttpUtil {
public static void apiConcurrent(String url,Map<String,String> params) {
        Integer count = 200;
        //创建线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        //同步工具
        CountDownLatch latch = new CountDownLatch(count);
        Map<String,String> dataMap = new HashMap<>(1);

        for (int i = 0; i < count; i++) {
            pool.execute(() -> {
                try {
                    //访问网关的API接口
                    HttpUtil.doGet("http://localhost:8081/bus-service/user/index",dataMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
public static String doGet(String url, Map<String, String> params) throws IOException {
        //创建HttpClient对象

    CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建uri
            URIBuilder builder = new URIBuilder(url);
            if (!params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri;
            uri = builder.build();
            //创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            List<NameValuePair> paramList = new ArrayList<>();
            RequestBuilder requestBuilder = RequestBuilder.get().setUri(new URI(url));
            requestBuilder.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
            httpGet.setHeader(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
            httpGet.setHeader(new BasicHeader("Accept", "*/*;charset=utf-8"));
            //执行请求
            response = httpClient.execute(httpGet);
            //判断返回状态是否是200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            log.info("调用失败:{}",e);
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
        log.info("打印：{}",resultString);
        return resultString;
    }
}