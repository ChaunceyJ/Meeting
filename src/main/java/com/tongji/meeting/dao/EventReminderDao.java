package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventReminder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventReminderDao {
    void create(EventReminder eventReminder);
}
