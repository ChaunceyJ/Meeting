package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventDetailDao {
    void create(EventDetail eventDetail);

    EventDetail retrieveByPK(int detailId);

    void delete(EventDetail eventDetail);
}
