package com.tongji.meeting.model;

public class Event {
    private int eventId;
    private int detailId;
    private int calendarId;
    private int priority;
    private int isOverriden;
    private int isRemind;
    //private EventDetail eventDetail;
    //get rid of detailId??


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getIsOverriden() {
        return isOverriden;
    }

    public void setIsOverriden(int isOverriden) {
        this.isOverriden = isOverriden;
    }

    public int getIsRemind() {
        return isRemind;
    }

    public void setIsRemind(int isRemind) {
        this.isRemind = isRemind;
    }
}
