package com.tongji.meeting.model;

import java.sql.Timestamp;

public class EventAllInfo {
    private Event event;
    private EventDetail eventDetail;

    public EventAllInfo(Event event, EventDetail eventDetail) {
        this.event = event;
        this.eventDetail = eventDetail;
    }

    public int getEventId() {
        return event.getEventId();
    }



    public int getPriority() {
        return event.getPriority();
    }



    public String getTitle() {
        return eventDetail.getTitle();
    }



    public String getContent() {
        return eventDetail.getContent();
    }



    public Timestamp getStartTime() {
        return eventDetail.getStartTime();
    }



    public Timestamp getEndTime() {
        return eventDetail.getEndTime();
    }


    public boolean isRepeat() {
        return eventDetail.isRepeat();
    }


//    public Timestamp getRemindTime() {
//        return
//    }

}
