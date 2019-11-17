package com.tongji.meeting.service;

import com.tongji.meeting.dao.UserDao;
import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    private RedisUtils redisUtils;

    public int insert(UserDomain record) {
        return userDao.insert(record);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void deleteUserById(int userId) {
        userDao.deleteUserById(userId);
    }

    public void updateUser(UserDomain userDomain) {
        userDao.updateUser(userDomain);
    }

    public List<UserDomain> selectUsers() {
        return userDao.selectUsers();
    }
}
