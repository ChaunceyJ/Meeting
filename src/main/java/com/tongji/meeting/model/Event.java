package com.tongji.meeting.model;

public class Event {
    private int eventId;
    private int detailId;
    private int calendarId;
    private int priority;
    private boolean isOverriden;
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

    public boolean isOverriden() {
        return isOverriden;
    }

    public void setOverriden(boolean overriden) {
        isOverriden = overriden;
    }
}
