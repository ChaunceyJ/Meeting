package com.tongji.meeting;

import com.tongji.meeting.model.Event;

import java.util.Date;
import java.util.List;

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
//        return Integer.parseInt(String.valueOf(
//                this.startTime.getTime() - timePeriod.startTime.getTime()));
        if (this.getStartTime().before(timePeriod.getStartTime())) {
            return -1;
        } else if (this.getStartTime().after(timePeriod.getStartTime())) {
            return 1;
        } else {
            return 0;
        }
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getLength() {
        return length;
    }

    public void setLength(Date length) {
        this.length = length;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isIntersect(TimePeriod other) {
        if (other.getStartTime().after(this.startTime)
                && other.getStartTime().before(this.endTime)) {
            return true;
        }
        if (other.getEndTime().before(this.endTime)
                && other.getEndTime().after(this.startTime)) {
            return true;
        }
        if (other.getStartTime().before(this.startTime)
                && other.getEndTime().after(this.endTime)) {
            return true;
        }
        if (other.getStartTime().compareTo(this.startTime) == 0){
            return true;
        }
        if (other.getEndTime().compareTo(this.endTime) == 0){
            return true;
        }
        return false;
    }
//    @Override
//    public String toString() {
//        return "MyNumber{" +
//                "key='" + key + '\'' +
//                ", value=" + value +
//                '}';
//    }

}
