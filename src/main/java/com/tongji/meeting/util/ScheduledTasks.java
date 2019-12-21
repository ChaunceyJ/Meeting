package com.tongji.meeting.util;

import com.tongji.meeting.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    NoticeService noticeService;

    @Scheduled(fixedDelay = 7100*1000)
    public void updateWechatAccessToken() {
//        WechatUtil.updateAccessTokenInRedis();
    }

    @Scheduled(fixedDelay =  60*1000)
    public void sendRemindMessage(){
        noticeService.sendMsgOfRemind();
    }

}