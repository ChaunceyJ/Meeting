package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventReminder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventReminderDao {
    void create(EventReminder eventReminder);

    List<EventReminder> getRemindEvent();

}
