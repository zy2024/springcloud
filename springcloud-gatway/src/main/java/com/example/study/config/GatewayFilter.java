package com.example.study.config;

import com.alibaba.fastjson.JSON;
import com.example.study.utils.ResponseResult;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class GatewayFilter  implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String url = request.getURI().getPath();
        log.info("接收到请求：{}", url);
//        response.setStatusCode(HttpStatus.OK);
//        return response.setComplete();

       return chain.filter(exchange);
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
