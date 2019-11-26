package com.tongji.meeting.service;

import com.tongji.meeting.dao.EventDao;
import com.tongji.meeting.dao.EventDetailDao;
import com.tongji.meeting.model.Event;
import com.tongji.meeting.model.EventDetail;
import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.util.GlobalValues;
import com.tongji.meeting.util.redis.RedisUtils;
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

//    public List<EventDetail> selectAll() {
//        return eventDetailDao.selectAll();
//    }

    public void addNewEvent(EventDetail eventDetail, Event event) {
        eventDetailDao.addNewEvent(eventDetail);
        //get just-inserted event_detail row primary key
        int detailId = eventDetail.getDetailId();
        event.setDetailId(detailId);
        eventDao.addNewEvent(event);
    }



}
