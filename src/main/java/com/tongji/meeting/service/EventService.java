package com.tongji.meeting.service;

import com.tongji.meeting.FreeTime;
import com.tongji.meeting.TimePeriod;
import com.tongji.meeting.dao.*;
import com.tongji.meeting.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventDetailDao eventDetailDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private UserCalendarDao userCalendarDao;

    @Autowired
    private UserDao userDao;


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
        userDetailDao.create(userDetail);
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

    public void deleteEvent(Event event, UserDomain userDomain) {
        //判断user是owner还是
        Event fullEvent = eventDao.retrieveByPK(event.getEventId());
        EventDetail eventDetail = eventDetailDao.retrieveByPK(fullEvent.getDetailId());

        UserDetail userDetail = userDetailDao.retrieveByPK(
                userDomain.getUserid(), fullEvent.getDetailId());

        if (userDetail.getPermission().equals("owner")) {
            //对于工作组，组长和创建者都是owner，或者只有组长
            eventDetailDao.delete(eventDetail);
            //顺便级联event, user_detail
        } else { //只是接受分享的人
            //成员？
            eventDao.delete(event);
            userDetailDao.delete(userDetail);
            //不删除eventDetail
        }
    }

    public List<EventAllInfo> getEventByCalendar(int calendarId){
        List<Event> eventList = eventDao.retrieveByCalendar(calendarId);
        List<EventAllInfo> results = new ArrayList<>();
        for (Event item : eventList) {
            EventDetail ed = eventDetailDao.retrieveByPK(item.getDetailId());
            results.add(new EventAllInfo(item, ed));
        }
        return results;
    }

    public List<EventAllInfo> getAllEventsOfOneUser(UserDomain userDomain) {
        //先按permission分类
        return new ArrayList<>();
    }

    public List<TimePeriod> recommend(int calendarId, Date duration, int priority){
        //考虑勿扰
        List<UserDomain> members = userDao.getAllMembers(calendarId);
//        List<EventAllInfo> publicInfo = new ArrayList<>();
//        List<EventAllInfo> privateInfo = new ArrayList<>();
//        for (UserDomain member : members) {
//            Boolean exposePublicDetail = userCalendarDao.retrieveIsExposeDetailByPK(member.getUserid(),calendarId);
//            if (exposePublicDetail) {
//                publicInfo.addAll(getAllEventsOfOneUser(member));
//            } else {
//                privateInfo.addAll(getAllEventsOfOneUser(member));
//            }
//        }
        List<EventAllInfo> allEvents = new ArrayList<>();
        for (UserDomain member : members) {
            allEvents.addAll(getAllEventsOfOneUser(member));
        }
        FreeTime ft = new FreeTime(allEvents);
        return ft.computeFreeTime();
    }


}
