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

    @Autowired
    private EventReminderDao eventReminderDao;

    @Autowired
    private EventRepetitionDao eventRepetitionDao;




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
        eventReminder.setEventId(event.getEventId());
        if (eventReminder.getRemindTime() != null) {
            eventReminderDao.create(eventReminder);
        }
        if (eventRepetition.getRepeatUnit() != null) {
            eventRepetition.setDetailId(detailId);
            eventRepetitionDao.create(eventRepetition);
        }
    }

    public String checkPermission(UserDetail userDetail) {
        userDetail = userDetailDao.retrieveByPK(userDetail.getUserId(),userDetail.getDetailId());

        return userDetail.getPermission();
    }

    public void modifyEvent(EventDetail eventDetail, Event newEvent,
                              Event oldEvent, UserDetail userDetail) {
        oldEvent = eventDao.retrieveByPK(oldEvent.getEventId());
//        System.out.println(oldEvent.getDetailId());
        userDetail.setDetailId(oldEvent.getDetailId());
//        System.out.println(checkPermission(userDetail));
        if (checkPermission(userDetail).equals("owner")) {
            eventDao.update(newEvent);
            eventDetailDao.update(eventDetail);
        }
        //如果涉及工作组的改变？？？
        // 删除userdetail里组员和该事件关联、新增新关联、
    }

    public void deleteEvent(Event event, UserDomain userDomain) {
        //判断user是owner还是
        Event fullEvent = eventDao.retrieveByPK(event.getEventId());
        EventDetail eventDetail = eventDetailDao.retrieveByPK(fullEvent.getDetailId());

        UserDetail userDetail = userDetailDao.retrieveByPK(
                userDomain.getUserid(), fullEvent.getDetailId());

        if (userDetail.getPermission().equals("owner")) {
            //对于工作组，组长和创建者都是owner，或者只有组长
            System.out.println("owner delete");
            System.out.println(eventDetail.getDetailId());
            eventDetailDao.delete(eventDetail);
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

//    public List<TimePeriod> recommend(int calendarId, Date duration, int priority){
//        //考虑勿扰
//        List<UserDomain> members = userCalendarDao.getAllMembers(calendarId);
////        List<EventAllInfo> publicInfo = new ArrayList<>();
////        List<EventAllInfo> privateInfo = new ArrayList<>();
////        for (UserDomain member : members) {
////            Boolean exposePublicDetail = userCalendarDao.retrieveIsExposeDetailByPK(member.getUserid(),calendarId);
////            if (exposePublicDetail) {
////                publicInfo.addAll(getAllEventsOfOneUser(member));
////            } else {
////                privateInfo.addAll(getAllEventsOfOneUser(member));
////            }
////        }
//        List<EventAllInfo> allEvents = new ArrayList<>();
//        for (UserDomain member : members) {
//            allEvents.addAll(getAllEventsOfOneUser(member));
//        }
//        FreeTime ft = new FreeTime(allEvents);
//        return ft.computeFreeTime();
//    }


    public List<Hashtable<String,Object>> showMyCalendar(int userId) {
        Calendar myCalendar = calendarDao.getMyCalendar(userId);
//        System.out.println("my calendar id");
//        System.out.println(myCalendar.getCalendarId());
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
        List<Event> eventsInMyCalendar = eventDao.retrieveByCalendar(myCalendar.getCalendarId());
        for (Event item : eventsInMyCalendar) {
            EventDetail ed = eventDetailDao.retrieveByPK(item.getDetailId());
//            System.out.println("eventid & detail id");
//            System.out.println(item.getEventId());
//            System.out.println(ed.getDetailId());
            EventAllInfo tt = new EventAllInfo(item, ed);
            Hashtable<String,Object> ht = new Hashtable<>();
            ht.put("title",tt.getTitle());
            ht.put("content",tt.getContent());
            ht.put("event_id",tt.getEventId());
            ht.put("priority",tt.getPriority());
            ht.put("is_repeat",tt.isRepeat());

            System.out.println(tt.getTitle());
            System.out.println("is Repeat:");
            System.out.println(tt.isRepeat());

            //当天0点
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);
            calendar.set(java.util.Calendar.MINUTE, 0);
            calendar.set(java.util.Calendar.SECOND, 0);
            calendar.set(java.util.Calendar.MILLISECOND,0);
            Date zero = calendar.getTime();

            if (tt.isRepeat() == 1) {
                java.util.Calendar original = java.util.Calendar.getInstance();
                original.setTime(tt.getStartTime());
                int weekDay = original.get(java.util.Calendar.DAY_OF_WEEK);

                java.util.Calendar base = java.util.Calendar.getInstance();
                base.add(java.util.Calendar.DATE,-3);

                java.util.Calendar st = java.util.Calendar.getInstance();
                st.setTime(tt.getStartTime());
                st.set(java.util.Calendar.YEAR, base.get(java.util.Calendar.YEAR));
                st.set(java.util.Calendar.MONTH, base.get(java.util.Calendar.MONTH));
                st.set(java.util.Calendar.DAY_OF_MONTH, base.get(java.util.Calendar.DAY_OF_MONTH));
                java.util.Calendar et = java.util.Calendar.getInstance();
                et.setTime(tt.getEndTime());
                et.set(java.util.Calendar.YEAR, base.get(java.util.Calendar.YEAR));
                et.set(java.util.Calendar.MONTH, base.get(java.util.Calendar.MONTH));
                et.set(java.util.Calendar.DAY_OF_MONTH, base.get(java.util.Calendar.DAY_OF_MONTH));


                for (int i =0; i < 10; i++) {
//                    System.out.println("========");
//                    System.out.println(i);
                    if (st.get(java.util.Calendar.DAY_OF_WEEK) == weekDay) {
                        System.out.println("进入if");
                        Hashtable<String,Object> htt = new Hashtable<>();
                        htt.put("title",tt.getTitle());
                        htt.put("content",tt.getContent());
                        htt.put("event_id",tt.getEventId());
                        htt.put("priority",tt.getPriority());
                        htt.put("is_repeat",tt.isRepeat());
                        htt.put("start_time",st.getTime());
                        htt.put("end_time",et.getTime());
//                        System.out.println(st.getTime());

                        switch(i) {
                            case 0:
                                d0.add(htt);
                                break;
                            case 1:
                                d1.add(htt);
                                break;
                            case 2:
                                d2.add(htt);
                                break;
                            case 3:
                                d3.add(htt);
                                break;
                            case 4:
                                d4.add(htt);
                                break;
                            case 5:
                                d5.add(htt);
                                break;
                            case 6:
                                d6.add(htt);
                                break;
                            case 7:
                                d7.add(htt);
                                break;
                            case 8:
                                d8.add(htt);
                                break;
                            case 9:
                                d9.add(htt);
                                break;

                        }
                    }
                    st.add(java.util.Calendar.DATE,1);
                    et.add(java.util.Calendar.DATE,1);
                }

            } else {
                double f = (tt.getStartTime().getTime() - zero.getTime())/ (24 * 60 * 60 * 1000.0);
                System.out.println(tt.getStartTime());
                System.out.println(tt.getStartTime().getTime());
                System.out.println(zero);
                System.out.println(zero.getTime());
                System.out.println(f);
                int diff = (int) Math.floor(f);
                System.out.println("======else=====");
                System.out.println(diff);
                ht.put("start_time",tt.getStartTime());
                ht.put("end_time",tt.getEndTime());
                switch(diff) {
                    case -3:
                        d0.add(ht);
                        break;
                    case -2:
                        d1.add(ht);
                        break;
                    case -1:
                        d2.add(ht);
                        break;
                    case 0:
                        d3.add(ht);
                        break;
                    case 1:
                        d4.add(ht);
                        break;
                    case 2:
                        d5.add(ht);
                        break;
                    case 3:
                        d6.add(ht);
                        break;
                    case 4:
                        d7.add(ht);
                        break;
                    case 5:
                        d8.add(ht);
                        break;
                    case 6:
                        d9.add(ht);
                        break;
                }
            }
        }

        List<Hashtable<String,Object>> results = new ArrayList<>();

        Hashtable<String,Object> temp0 = new Hashtable<>();
        temp0.put("day_index",-3);
        temp0.put("event",d0);
        results.add(temp0);

        Hashtable<String,Object> temp1 = new Hashtable<>();
        temp1.put("day_index",-2);
        temp1.put("event",d1);
        results.add(temp1);

        Hashtable<String,Object> temp2 = new Hashtable<>();
        temp2.put("day_index",-1);
        temp2.put("event",d2);
        results.add(temp2);

        Hashtable<String,Object> temp3 = new Hashtable<>();
        temp3.put("day_index",0);
        temp3.put("event",d3);
        results.add(temp3);

        Hashtable<String,Object> temp4 = new Hashtable<>();
        temp4.put("day_index",1);
        temp4.put("event",d4);
        results.add(temp4);

        Hashtable<String,Object> temp5 = new Hashtable<>();
        temp5.put("day_index",2);
        temp5.put("event",d5);
        results.add(temp5);

        Hashtable<String,Object> temp6 = new Hashtable<>();
        temp6.put("day_index",3);
        temp6.put("event",d6);
        results.add(temp6);

        Hashtable<String,Object> temp7 = new Hashtable<>();
        temp7.put("day_index",4);
        temp7.put("event",d7);
        results.add(temp7);

        Hashtable<String,Object> temp8 = new Hashtable<>();
        temp8.put("day_index",5);
        temp8.put("event",d8);
        results.add(temp8);

        Hashtable<String,Object> temp9 = new Hashtable<>();
        temp9.put("day_index",6);
        temp9.put("event",d9);
        results.add(temp9);


        return results;
    }
}
