package com.tongji.meeting.util;

import com.tongji.meeting.util.redis.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisUtilsTest {
    @Autowired
    RedisUtils ru = new RedisUtils();

    @Test
    void hmget() {
        System.out.println(ru.hmget("3sessionKey"));
    }

    @Test
    void hmset() {
        Map<String,Object> info = new HashMap<>();
        info.put("openid", "123");
        info.put("session_key", "123");
        ru.hmset("3sessionKey", info, 10000);
    }
}