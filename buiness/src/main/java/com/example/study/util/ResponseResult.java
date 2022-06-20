package com.example.study.util;

import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;

/**
 * @author 阿星
 */

public class ResponseResult<T> implements Serializable {
    private int code;
    private String msg;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(int code) {
        this.code = code;
    }

    private ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static <T>ResponseResult<T> success(){
        ResponseResult responseResult = new ResponseResult<T>(200,"成功");
        return responseResult;
    }
    public static <T>ResponseResult<T> success(String msg){
        ResponseResult responseResult = new ResponseResult<T>(200,msg);
        return responseResult;
    }
    public static <T>ResponseResult<T> success(String msg,T data){
        ResponseResult responseResult = new ResponseResult<T>(200,msg,data);
        return responseResult;
    }
    public static <T>ResponseResult<T> error(){
        ResponseResult responseResult = new ResponseResult<T>(500,"服务器错误");
        return responseResult;
    }

    public static <T>ResponseResult<T> error(String msg){
        ResponseResult responseResult = new ResponseResult<T>(500,msg);
        return responseResult;
    }
}
