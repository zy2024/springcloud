package com.example.study.controller;

import com.example.study.log.Log;
import com.example.study.entiy.User;
import com.example.study.service.UserService;
import com.example.study.util.IdUtils;
import com.example.study.util.JwtUtils;

import com.example.study.util.Md5Utils;
import com.example.study.util.ResponseResult;
import com.example.study.utils.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")

public class LoginController  {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserService userService;
    @Resource
    private RedisUtil redisUtil;
    @RequestMapping("/toLogin")
    @Log
    public String  tpLogin(HttpServletRequest request, HttpServletResponse response, @Param("userName")String userName, @Param("pwd")String pwd) throws IOException {

        UsernamePasswordToken token = new UsernamePasswordToken(userName,pwd);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User userInfo = (User)subject.getPrincipal();
        String cooick = JwtUtils.createToken(userInfo.getId());
       redisUtil.set(userInfo.getId(),cooick);
        response.setHeader("token",cooick);
        return "index.html";
    }

    @RequestMapping("/error")
    public String error(){
        return "error/404.html";
    }

    @GetMapping("/index")
    public String login() {
        return "page/login.html";
    }
    @GetMapping("/sendSsm")
    @Log
    public ResponseResult sendSsm(String phone){
        if(redisUtil.hasKey(phone)){
            if(redisUtil.getExpire(phone)>0) {
                return ResponseResult.error("验证码还在有效中");
            }
        }
        Long codeL = System.nanoTime();
        String codeStr = Long.toString(codeL);
        String verifyCode = codeStr.substring(codeStr.length() - 6);
        //60秒过期
        redisUtil.set(phone,verifyCode,60);
        rabbitTemplate.convertAndSend("ssm","ssm",phone+":"+verifyCode);
        return ResponseResult.success("短信发送成功");

    }
//    @Log(value = "登录")
    @PostMapping("/register")
    @Log
    public ResponseResult register(@RequestBody User user){
        user.setSalt(Md5Utils.randomSalt());
        String newPassWord = Md5Utils.md5(user.getPwd(),user.getSalt());
        user.setPwd(newPassWord);
        user.setId(IdUtils.getRandomIdByUUID());
        userService.register(user);
        return ResponseResult.success("注册成功");
    }

    @GetMapping("/getInfo")
    public ResponseResult getList(){
        Map map=new ConcurrentHashMap();
        Set set = map.entrySet();
        System.out.printf("");
        return ResponseResult.success("ok");
    }
}
