package com.tongji.meeting.service;

import com.tongji.meeting.dao.UserDao;
import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.UserCalendar;
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
    @Autowired
    private UserCalendarService userCalendarService;
    @Autowired
    private CalendarService calendarService;

    public int insertNewUser(UserDomain record) {
        int re = userDao.insert(record);
        updateSessionKey(record);
        int newCalendarId;
        Calendar calendar=new Calendar();
        UserCalendar userCalendar=new UserCalendar();
        calendar.setCalendarName("我的日历");
        userCalendar.setUserId(record.getUserid());
        userCalendar.setDisturb(false);
        userCalendar.setRole("mine");
        userCalendar.setDetailExposed(false);
        calendarService.createCalendar(calendar);
        newCalendarId=calendar.getCalendarId();
        userCalendar.setCalendarId(newCalendarId);
        userCalendarService.createUCRelation(userCalendar);
        return re;
    }

    public void updateUserName(UserDomain user){
        userDao.updateUser(user);
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

    public void updateUserName(UserDomain user){
        userDao.updateUser(user);
    }
}
