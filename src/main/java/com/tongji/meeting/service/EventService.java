package com.tongji.meeting.service;

import com.tongji.meeting.dao.EventDao;
import com.tongji.meeting.dao.EventDetailDao;
import com.tongji.meeting.dao.UserDeatilDao;
import com.tongji.meeting.model.Event;
import com.tongji.meeting.model.EventDetail;
import com.tongji.meeting.model.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventDetailDao eventDetailDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDeatilDao userDeatilDao;

    //创建权限？
    public int addNewEvent(EventDetail eventDetail, Event event, UserDetail userDetail) {
        eventDetailDao.create(eventDetail);
        //get just-inserted event_detail row primary key
        int detailId = eventDetail.getDetailId();
        event.setDetailId(detailId);
        eventDao.create(event);
        userDetail.setDetailId(detailId);
        userDetail.setPermission("owner");
        //关联组员
        userDeatilDao.create(userDetail);
        return 1;
    }

    public String checkPermission(UserDetail userDetail) {
        //update
        return "2";
    }

    public String modifyEvent(EventDetail eventDetail, Event event, UserDetail userDetail) {
        //update
        checkPermission(userDetail);
        //event.setCalendarId(calendarId);
        //如果涉及工作组的改变？？？
        // 删除userdetail里组员和该事件关联、新增新关联、
        return "1";
    }



}
