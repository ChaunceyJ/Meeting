package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface EventDetailDao {
    //int insertEventDetail(EventDetail eventDetail);
    List<EventDetail> selectAll();
}
