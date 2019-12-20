package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventRepetition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventRepetitionDao {
    void create(EventRepetition eventRepetition);
}
