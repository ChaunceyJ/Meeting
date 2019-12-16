package com.tongji.meeting.dao;

import com.tongji.meeting.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventDao {

    void create(Event event);
    void delete(Event event);
    List<Event> retrieveByCalendar(Calendar calendar);
}
