package com.tongji.meeting.util;

import com.alibaba.fastjson.JSONObject;
import com.tongji.meeting.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private RedisUtils redisUtils;

//    @Scheduled(fixedDelay = 7000*1000)
//    public void updateWechatAccessToken() {
//        JSONObject data = WechatUtil.getAccessToken();
//        if (data.containsKey("errcode")&&data.getInteger("errcode")!=0){
//            System.out.println(data.getString("errmsg"));
//        }else {
//            String access_token = data.getString("access_token");
//            long expires_in = data.getLong("expires_in");
//            redisUtils.set("access_token", access_token, expires_in);
//        }
//    }

    @Scheduled(fixedDelay =  60*1000)
    public void sendRemindMessage(){
        if (!redisUtils.hasKey("access_token")){
//            updateWechatAccessToken();
        }
        String access_token = (String) redisUtils.get("access_token");
        String template_id = "";//发送的提醒模版编号
        //查出需要提醒的用户，向用户发送提醒(循环)
        JSONObject data = new JSONObject();
        //WechatUtil.sendMessage(access_token, , template_id, data);
    }

}