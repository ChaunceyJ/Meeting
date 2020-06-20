package com.tongji.meeting;

import com.tongji.meeting.model.EventAllInfo;

import java.text.SimpleDateFormat;
import java.util.*;

public class FreeTime {
    private List<EventAllInfo> eventAllInfos;
    private int timeNeededDuration;//seconds

    public FreeTime(List<EventAllInfo> eventAllInfos, int timeNeededDuration) {
        this.eventAllInfos = eventAllInfos;
        this.timeNeededDuration = timeNeededDuration;
    }

    public List<TimePeriod> computeFreeTime() {
        List<TimePeriod> occupied = new ArrayList<>();
        List<TimePeriod> spareTime = new ArrayList<>();
        // eventAllInfos 应该是now之后的事件们
        for (EventAllInfo item : eventAllInfos) {
            occupied.add(new TimePeriod(item.getStartTime(), item.getEndTime()));
        }
        Collections.sort(occupied);//按开始时间排序
        Date st = new Date();
        Date et = new Date();
        for (TimePeriod tp : occupied) {
            st = tp.getStartTime();
            // 两天之间不判断
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            if (!fmt.format(st).equals(fmt.format(et))) { //不是同一天
                //et是昨天， st是第二天
                java.util.Calendar calendar11 = java.util.Calendar.getInstance();
                calendar11.setTime(et);
                calendar11.set(java.util.Calendar.HOUR_OF_DAY, 23);
                calendar11.set(java.util.Calendar.MINUTE, 59);
                calendar11.set(java.util.Calendar.SECOND, 0);
                calendar11.set(java.util.Calendar.MILLISECOND, 0);
                Date boundary = calendar11.getTime();
                int delta = (int) (boundary.getTime() - et.getTime()) / 1000 / 60;
                if (delta >= this.timeNeededDuration) {
                    spareTime.add(new TimePeriod(et, boundary));
                }
                et = tp.getEndTime();
                continue;
            }
            if (st.after(et)) {
                int delta = (int) (st.getTime() - et.getTime()) / 1000 / 60;
                if (delta >= this.timeNeededDuration) {
                    spareTime.add(new TimePeriod(et, st));
                }
            }

            if (et.after(tp.getEndTime())) {
                et = tp.getEndTime();
            }
        }

        //最后一个事件的那天的晚上
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(et);

        calendar.set(java.util.Calendar.HOUR_OF_DAY, 23);
        calendar.set(java.util.Calendar.MINUTE, 59);
        calendar.set(java.util.Calendar.SECOND, 0);
        calendar.set(java.util.Calendar.MILLISECOND, 0);
        Date boundary = calendar.getTime();
        int delta = (int) (boundary.getTime() - et.getTime()) / 1000 / 60;
        if (delta >= this.timeNeededDuration) {
            spareTime.add(new TimePeriod(et, boundary));
        }
        return spareTime;
    }
}
