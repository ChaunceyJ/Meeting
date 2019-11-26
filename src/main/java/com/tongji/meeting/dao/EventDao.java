package com.tongji.meeting.dao;

import com.tongji.meeting.model.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EventDao {

    void addNewEvent(Event event);

}
