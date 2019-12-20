package com.tongji.meeting.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeServiceTest {
    @Autowired
    NoticeService noticeService;

    @Test
    void sendMsgOfUngroup() {
        noticeService.sendMsgOfUngroup(1, "dqd");
    }

    @Test
    void sendMsgOfRemind() {
    }
}