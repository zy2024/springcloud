package com.example.study.config;

import com.alibaba.fastjson.JSON;
import com.example.study.utils.ResponseResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();

        String url = request.getURI().getPath();
        log.info("接收到请求：{}", url);
        //放行请求
      //  if (url.contains("/bus-service/user/toLogin")) {
        if (true) {
            return chain.filter(exchange);

        } else {
            HttpHeaders headers1 = request.getHeaders();
            List<String> list = headers1.get("token");
            //这里写用户身份认证/token解析的代码
            if (null != list&&!list.get(0).equals("")) {
                return chain.filter(exchange);
            }
            //没有token直接返回拦截
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            response.getHeaders().add("Content-Type", "application/json;charset=utf-8");
            DataBuffer wrap = response.bufferFactory().wrap("请登录后请求".getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Flux.just(wrap));
        }
//        response.setStatusCode(HttpStatus.OK);
//        return response.setComplete();

    }

    @Override
    public int getOrder() {
        return 1;
    }

    /**
     * 设置响应体
     **/
    public Mono<Void> responseBody(ServerWebExchange exchange, Integer code, String msg) {
        String message = JSON.toJSONString(new ResponseResult<>(code, msg));
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        return this.responseHeader(exchange).getResponse()
                .writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(bytes)));
    }

    /**
     * 设置响应体的请求头
     */
    public ServerWebExchange responseHeader(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
        return exchange.mutate().response(response).build();
    }
}
