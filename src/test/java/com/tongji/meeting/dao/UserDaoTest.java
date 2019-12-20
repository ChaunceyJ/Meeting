package com.tongji.meeting.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    void getAllMembers(){
        List re = userDao.getAllMembers(1);
        System.out.println(re);
    }



}