package com.tongji.meeting.service;

import com.alibaba.fastjson.JSONObject;
import com.tongji.meeting.dao.CalendarDao;
import com.tongji.meeting.dao.EventReminderDao;
import com.tongji.meeting.dao.UserDao;
import com.tongji.meeting.model.Calendar;
import com.tongji.meeting.model.EventReminder;
import com.tongji.meeting.model.UserDomain;
import com.tongji.meeting.util.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    UserDao userDao;
    @Autowired
    CalendarDao calendarDao;
    @Autowired
    EventReminderDao eventReminderDao;

    /**
     * 群组名称{{thing1.DATA}}
     *
     * 群组描述{{thing2.DATA}}
     *
     * 群主{{name3.DATA}}
     *
     * 解散时间{{date4.DATA}}
     *
     * 备注{{thing5.DATA}}
     */
    public void sendMsgOfUngroup(int calendarId, String userName){
        String templateID = "hPnT-GCD8EUANoj5DgBRsUuwoOxli1Anz0fms2iGFd4";

        JSONObject data = new JSONObject();
        JSONObject groupName = new JSONObject();
        List<Calendar> calendars = calendarDao.getCalendarById(calendarId);
        groupName.put("value", calendars.get(0).getCalendarName());
        data.put("thing1", groupName);
        JSONObject detail = new JSONObject();
        detail.put("value", "");
        data.put("thing2", detail);
        JSONObject owner = new JSONObject();
        owner.put("value", userName);
        data.put("name3", owner);
        JSONObject time = new JSONObject();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");//设置日期格式
        time.put("value", df.format(new Date()));
        data.put("date4", time);
        JSONObject note = new JSONObject();
        note.put("value", "");
        data.put("thing5", note);

        List<UserDomain> lists = userDao.getAllMembersOpenid(calendarId);
        for (UserDomain u:lists
             ) {
            WechatUtil.sendMessage(u.getOpenid(), templateID, data);
        }

    }

    /**
     * 工作标题{{thing1.DATA}}
     *
     * 工作内容{{thing2.DATA}}
     *
     * 开始时间{{date3.DATA}}
     *
     * 负责人{{name5.DATA}}
     *
     * 完成时间{{date4.DATA}}
     */
    public void sendMsgOfRemind(){
        String templateID = "k6W96QUyzo_yhzkk54qcAm78fOmTr7OGr0x5gKGsKsU";
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");//设置日期格式
        List<EventReminder> ls =  eventReminderDao.getRemindEvent();
        for (EventReminder l:ls
             ) {
            JSONObject data = new JSONObject();
            JSONObject title = new JSONObject();
            title.put("value", l.getEventDetail().getTitle());
            data.put("thing1", title);
            JSONObject detail = new JSONObject();
            detail.put("value", l.getEventDetail().getContent());
            data.put("thing2", detail);
            JSONObject begin = new JSONObject();
            begin.put("value", df.format(l.getEventDetail().getStartTime()));
            data.put("data3", begin);
            JSONObject name = new JSONObject();
            name.put("value", l.getUser().getName());
            data.put("name5", name);
            JSONObject end = new JSONObject();
            end.put("value", df.format(l.getEventDetail().getEndTime()));
            data.put("date4", end);
            WechatUtil.sendMessage(l.getUser().getOpenid(), templateID, data);
        }
    }
}
