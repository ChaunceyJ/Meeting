package com.tongji.meeting.dao;

import com.tongji.meeting.model.EventReminder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventReminderDaoTest {

    @Autowired
    EventReminderDao eventReminderDao;

    @Test
    void test(){
        List<EventReminder> ls =  eventReminderDao.getRemindEvent();
        System.out.println(ls);
    }

}