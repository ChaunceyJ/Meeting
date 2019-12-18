package com.tongji.meeting.dao;

import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventDao {

    void create(Event event);
    void delete(Event event);
    List<Event> retrieveByCalendar(int calendarId);

    Event retrieveByPK(int detailId);
}
