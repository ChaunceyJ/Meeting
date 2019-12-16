package com.tongji.meeting.service;

import com.tongji.meeting.model.UserDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;
    UserDomain user = new UserDomain();

    @Test
    void insertNewUser() {
        user.setSession_key("2333");
        user.setMy_session_key("0000");
        user.setOpenid("12");
        userService.insertNewUser(user);
        System.out.println(user.getUserid());
    }

    @Test
    void selectUserByOpenid() {
        UserDomain user2 = userService.selectUserByOpenid("12");
        System.out.println(user2);
    }

    @Test
    void updateSessionKey() {

    }
}