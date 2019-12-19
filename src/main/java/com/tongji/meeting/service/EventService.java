package com.tongji.meeting.service;

import com.tongji.meeting.FreeTime;
import com.tongji.meeting.TimePeriod;
import com.tongji.meeting.dao.*;
import com.tongji.meeting.model.*;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
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
    private CalendarDao calendarDao;


    //能发请求的都是owner
    public void addNewEvent(EventDetail eventDetail, Event event,
                             UserDetail userDetail, EventReminder eventReminder,
                             EventRepetition eventRepetition) {
        eventDetailDao.create(eventDetail);
        //get just-inserted event_detail row primary key
        int detailId = eventDetail.getDetailId();
        event.setDetailId(detailId);
        eventDao.create(event);
        userDetail.setDetailId(detailId);
        userDetail.setPermission("owner");
        //关联组员
        eventReminder.setEventId(detailId);
        eventRepetition.setDetailId(detailId);
        userDetailDao.create(userDetail);
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
        } else { //只是接受分享的人???或者其他组员？
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
        List<UserDomain> members = userCalendarDao.getAllMembers(calendarId);
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


    public List<Hashtable<String,Object>> showMyCalendar(int userId) {
        int myCalendarId = calendarDao.getMyCalendar(userId);

        List<Hashtable<String,Object>> d0 = new ArrayList<>();//[{event1}.{event2}]
        List<Hashtable<String,Object>> d1 = new ArrayList<>();
        List<Hashtable<String,Object>> d2 = new ArrayList<>();
        List<Hashtable<String,Object>> d3 = new ArrayList<>();
        List<Hashtable<String,Object>> d4 = new ArrayList<>();
        List<Hashtable<String,Object>> d5 = new ArrayList<>();
        List<Hashtable<String,Object>> d6 = new ArrayList<>();
        List<Hashtable<String,Object>> d7 = new ArrayList<>();
        List<Hashtable<String,Object>> d8 = new ArrayList<>();
        List<Hashtable<String,Object>> d9 = new ArrayList<>();

        //去除被覆盖的事件
        List<Event> eventsInMyCalendar = eventDao.retrieveByCalendar(myCalendarId);
        for (Event item : eventsInMyCalendar) {
            EventDetail ed = eventDetailDao.retrieveByPK(item.getDetailId());
            int diff = (int)(ed.getStartTime().getTime() - (new Date()).getTime())/ (24 * 60 * 60 * 1000);
            System.out.println(diff);
            EventAllInfo tt = new EventAllInfo(item, ed);
            Hashtable<String,Object> ht = new Hashtable<>();
            ht.put("title",tt.getTitle());
            ht.put("content",tt.getContent());
            ht.put("start_time",tt.getStartTime());
            ht.put("end_time",tt.getEndTime());
            ht.put("event_id",tt.getEventId());
            ht.put("priority",tt.getPriority());
//                ht.put("title",);

            switch(diff) {
                case -3:
                    d0.add(ht);
                    break;
                case -2:
                    d0.add(ht);
                    break;
                case -1:
                    d0.add(ht);
                    break;
                case 0:
                    d0.add(ht);
                    break;
                case 1:
                    d0.add(ht);
                    break;
                case 2:
                    d0.add(ht);
                    break;
                case 3:
                    d0.add(ht);
                    break;
                case 4:
                    d0.add(ht);
                    break;
                case 5:
                    d0.add(ht);
                    break;
                case 6:
                    d0.add(ht);
                    break;

            }
        }

        List<Hashtable<String,Object>> results = new ArrayList<>();

        Hashtable<String,Object> temp0 = new Hashtable<>();
        temp0.put("day_index",0);
        temp0.put("event",d0);
        results.add(temp0);

        Hashtable<String,Object> temp1 = new Hashtable<>();
        temp1.put("day_index",1);
        temp1.put("event",d1);
        results.add(temp1);

        Hashtable<String,Object> temp2 = new Hashtable<>();
        temp2.put("day_index",2);
        temp2.put("event",d2);
        results.add(temp2);

        Hashtable<String,Object> temp3 = new Hashtable<>();
        temp3.put("day_index",3);
        temp3.put("event",d3);
        results.add(temp3);

        Hashtable<String,Object> temp4 = new Hashtable<>();
        temp4.put("day_index",4);
        temp4.put("event",d4);
        results.add(temp4);

        Hashtable<String,Object> temp5 = new Hashtable<>();
        temp5.put("day_index",5);
        temp5.put("event",d5);
        results.add(temp5);

        Hashtable<String,Object> temp6 = new Hashtable<>();
        temp6.put("day_index",6);
        temp6.put("event",d6);
        results.add(temp6);

        Hashtable<String,Object> temp7 = new Hashtable<>();
        temp7.put("day_index",7);
        temp7.put("event",d7);
        results.add(temp7);

        Hashtable<String,Object> temp8 = new Hashtable<>();
        temp8.put("day_index",8);
        temp8.put("event",d8);
        results.add(temp8);

        Hashtable<String,Object> temp9 = new Hashtable<>();
        temp9.put("day_index",9);
        temp9.put("event",d9);
        results.add(temp9);


        return results;
    }
}
