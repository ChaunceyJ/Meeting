package com.tongji.meeting.util;

import java.util.Date;

public class TimePeriod implements Comparable<TimePeriod>{
    Date startTime;
    Date length;
    Date endTime;

    public TimePeriod(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(TimePeriod timePeriod) {
//        Integer date = Integer.valueOf(String.valueOf(new Date().getTime()).substring(0, 10));
        return Integer.parseInt(String.valueOf(
                this.startTime.getTime() - timePeriod.startTime.getTime()));
    }

//    @Override
//    public String toString() {
//        return "MyNumber{" +
//                "key='" + key + '\'' +
//                ", value=" + value +
//                '}';
//    }

}
