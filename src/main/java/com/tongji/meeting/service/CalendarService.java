package com.tongji.meeting.service;

import com.tongji.meeting.dao.CalendarDao;
import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.UserCalendar;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private CalendarDao calendarDao;

    public void createCalendar(Calendar calendar){
        calendarDao.createCalendar(calendar);
    }
    public void disbandCalendar(Integer calendarId){calendarDao.disbandCalendar(calendarId);}

    public List<Calendar> getCreatedCalendar(Integer userId){
        return calendarDao.getCreatedCalendar(userId);
    }

    public List<Calendar> getParticipantCalendar(Integer userId){
        return calendarDao.getParticipantCalendar(userId);
    }

    public Calendar getMyCalendar(Integer userId){
        return calendarDao.getMyCalendar(userId);
    }
}
