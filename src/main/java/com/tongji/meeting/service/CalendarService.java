package com.tongji.meeting.service;

import com.tongji.meeting.dao.CalendarDao;
import com.tongji.meeting.dao.EventDao;
import com.tongji.meeting.dao.UserCalendarDao;
import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.UserCalendar;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private CalendarDao calendarDao;

    @Autowired
    private UserCalendarDao userCalendarDao;

    @Autowired
    private EventDao eventDao;

    public void createCalendar(Calendar calendar){
        calendarDao.createCalendar(calendar);
    }
    public void disbandCalendar(Integer calendarId){calendarDao.disbandCalendar(calendarId);}

    public Hashtable<String,Object> getCreatedCalendar(Integer userId){
        List<Calendar> calendarList=calendarDao.getCreatedCalendar(userId);
        List<Integer> memberNum=new ArrayList<>();
        List<Integer> eventNum=new ArrayList<>();
        int calendarId;
        for(Calendar calendar:calendarList){
            calendarId=calendar.getCalendarId();
            memberNum.add(userCalendarDao.getMemberNum(calendarId));
            eventNum.add(eventDao.getEventNum(calendarId));
        }
        Hashtable<String,Object> createdCalendar = new Hashtable<>();
        createdCalendar.put("calendarList",calendarList);
        createdCalendar.put("memberNum",memberNum);
        createdCalendar.put("eventNum",eventNum);
        return createdCalendar;
    }

    public Hashtable<String,Object> getParticipantCalendar(Integer userId){
        List<Calendar> calendarList=calendarDao.getParticipantCalendar(userId);
        List<Integer> memberNum=new ArrayList<>();
        List<Integer> eventNum=new ArrayList<>();
        int calendarId;
        for(Calendar calendar:calendarList){
            calendarId=calendar.getCalendarId();
            memberNum.add(userCalendarDao.getMemberNum(calendarId));
            eventNum.add(eventDao.getEventNum(calendarId));
        }
        Hashtable<String,Object> participantCalendar = new Hashtable<>();
        participantCalendar.put("calendarList",calendarList);
        participantCalendar.put("memberNum",memberNum);
        participantCalendar.put("eventNum",eventNum);
        return participantCalendar;
    }

    public Calendar getMyCalendar(Integer userId){
        return calendarDao.getMyCalendar(userId);
    }
}
