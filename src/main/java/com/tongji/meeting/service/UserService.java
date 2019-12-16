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
        updateSessionKey(record);
        return re;
    }

    public void updateSessionKey(UserDomain record){
        HashMap<String, Object> info = new HashMap<>();
        info.put("userid", record.getUserid());
        info.put("openid", record.getOpenid());
        info.put("session_key", record.getSession_key());
        redisUtils.hmset(record.getMy_session_key(), info, GlobalValues.mySessionTime);
    }

    public int insert(UserDomain record) {
        return userDao.insert(record);
    }

    public UserDomain selectUserByOpenid(String openid) {
        List<UserDomain> records = userDao.selectUsersByOpenid(openid);
        if (records.size() == 0){
            return null;
        }else {
            return records.get(0);
        }
    }
}
