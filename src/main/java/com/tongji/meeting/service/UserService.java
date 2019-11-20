package com.tongji.meeting.service;

import com.tongji.meeting.dao.UserDao;
import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.util.GlobalValues;
import com.tongji.meeting.util.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    public int insertNewUser(UserDomain record) {
        int re = userDao.insert(record);
        HashMap<String, Object> info = new HashMap<>();
        info.put("openid", record.getOpenid());
        info.put("session_key", record.getSession_key());
        redisUtils.hmset(record.getMy_session_key(), info, GlobalValues.mySessionTime);
        return re;
    }

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

    public UserDomain selectUserByOpenid(String openid) {
        return userDao.selectUsersByOpenid(openid);
    }
}
