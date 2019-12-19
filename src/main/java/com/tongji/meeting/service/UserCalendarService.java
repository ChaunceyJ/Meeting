package com.tongji.meeting.service;

import com.tongji.meeting.dao.CalendarDao;
import com.tongji.meeting.dao.UserCalendarDao;
import com.tongji.meeting.dao.UserDao;
import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.UserCalendar;
import com.tongji.meeting.model.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCalendarService {
    @Autowired
    private UserCalendarDao userCalendarDao;

    @Autowired
    private UserDao userDao;

    public void createUCRelation(UserCalendar userCalendar){
        userCalendarDao.createUCRelation(userCalendar);
    }

    public void quitCalendar(Integer userId,Integer calendarId){
        userCalendarDao.quitCalendar(userId,calendarId);
    }

    public void setNoDisturb(Integer userId,Integer calendarId,Boolean disturb){userCalendarDao.setNoDisturb(userId,calendarId,disturb);}

    public void setDetailExposed(Integer userId,Integer calendarId,Boolean disturb){userCalendarDao.setDetailExposed(userId,calendarId,disturb);}

    public List<UserDomain> getAllMembers(Integer calendarId){
        return userDao.getAllMembers(calendarId);
    }
}
