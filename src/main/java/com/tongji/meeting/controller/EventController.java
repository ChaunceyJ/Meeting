package com.tongji.meeting.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.service.UserService;
import com.tongji.meeting.util.GlobalValues;
import com.tongji.meeting.util.redis.RedisUtils;
import com.tongji.meeting.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
public class EventController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user" , method = RequestMethod.POST ,produces = "application/json")
    public ResponseEntity addUser(
            @RequestParam(value = "userName", required = true)
                    String userName,
            @RequestParam(value = "openid", required = true)
                    String openid
    ){

        HashMap result = new HashMap<String, String>();
//        // 1.接收小程序发送的code
//        // 2.开发者服务器 登录凭证校验接口 appid + appsecret + code
//        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
//        // 3.接收微信接口服务 获取返回的参数
//
//        if (SessionKeyOpenId.getInteger("errcode").intValue() != 0){
//            System.out.println(SessionKeyOpenId.getString("errmsg"));
//            return ResponseEntity.badRequest().body(SessionKeyOpenId.getString("errmsg"));
//        }
//        String openid = SessionKeyOpenId.getString("openid");
//        String sessionKey = SessionKeyOpenId.getString("session_key");
//        // 4.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新登录
//        UserDomain user = userService.selectUserByOpenid(openid);
//        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
//        boolean only = true;
//        String skey = null;
//
//        //5. 把新的skey返回给小程序
//        result.put("skey", skey);
//        return ResponseEntity.ok(result);

        return ResponseEntity.ok("添加成功");
    }

}
