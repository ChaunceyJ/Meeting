package com.tongji.meeting.controller;

import com.alibaba.fastjson.JSONObject;
import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.service.UserService;
import com.tongji.meeting.util.GlobalValues;
import com.tongji.meeting.util.redis.RedisUtils;
import com.tongji.meeting.util.WechatUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@Api("用户模块")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "用户登录", notes="用户首次使用则存入用户表中；每次调用都会更新uuid")
    @RequestMapping(value = "/login" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity loginByWechat(
            @RequestParam(value = "code", required = true) String code){
        HashMap result = new HashMap<String, String>();
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appid + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数

        if (SessionKeyOpenId.containsKey("errcode") && SessionKeyOpenId.getInteger("errcode").intValue() != 0){
            System.out.println(SessionKeyOpenId.getString("errmsg"));
            return ResponseEntity.badRequest().body(SessionKeyOpenId.getString("errmsg"));
        }
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 4.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新登录
        UserDomain user = userService.selectUserByOpenid(openid);
        // uuid生成唯一key，用于维护微信小程序用户与服务端的会话
        boolean only = true;
        String skey = null;
        while (only){
            skey = UUID.randomUUID().toString();
            only = redisUtils.hasKey(skey);
        }
        if (user == null){
            user = new UserDomain();
            user.setOpenid(openid);
            user.setSession_key(sessionKey);
            user.setMy_session_key(skey);
            userService.insertNewUser(user);
        }else {
            HashMap<String, Object> info = new HashMap<>();
            info.put("openid", user.getOpenid());
            info.put("session_key", sessionKey);
            info.put("userid", user.getUserid());
            redisUtils.hmset(skey, info, GlobalValues.mySessionTime);
        }
        //5. 把新的skey返回给小程序
        result.put("skey", skey);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "用户修改名字", notes="根据用户微信名字修改名字")
    @RequestMapping(value = "/api/user/changeName" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity changeUserName(
            @RequestHeader(value = "Authorization",required = true)String sKey,
            @RequestParam(value = "userName",required = true)String userName
    ){
//        System.out.println(sKey);
        int userId = (int)redisUtils.hget(sKey, "userid");
        UserDomain user = new UserDomain();
        user.setUserid(userId);
        user.setName(userName);
        userService.updateUserName(user);
        return ResponseEntity.ok("success");
    }

}
