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
        user.setSession_key("12er3456");
        user.setMy_session_key("123e456");
        user.setOpenid("1234wrt56");
        userService.insertNewUser(user);
        System.out.println(user.getUserid());
        user.setName("cdcdcd");
        userService.updateUserName(user);

    }

    @Test
    void selectUserByOpenid() {
        UserDomain user2 = userService.selectUserByOpenid("1762");
        System.out.println(user2.getUserid());
    }

    @Test
    void updateSessionKey() {

    }
}