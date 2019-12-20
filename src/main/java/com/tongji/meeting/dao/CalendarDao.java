package com.tongji.meeting.dao;

import com.tongji.meeting.model.UserCalendar;
import org.apache.ibatis.annotations.Mapper;

import com.tongji.meeting.model.Calendar;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CalendarDao {
    void createCalendar(Calendar calendar);
    void disbandCalendar(Integer calendarId);
    List<Calendar> getCreatedCalendar(Integer userId);
    List<Calendar> getParticipantCalendar(Integer userId);
    Calendar getMyCalendar(Integer userId);
    int memberNum(Integer calendarId);
    List<Calendar> getCalendarById(Integer calendarId);
}
