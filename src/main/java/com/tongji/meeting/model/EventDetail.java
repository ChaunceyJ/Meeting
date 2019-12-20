package com.tongji.meeting.model;

import java.util.Date;

public class EventDetail {
    private int detailId;
    private String title;
    private String content;
    private Date startTime;
    private Date endTime;
    private int isRepeat;
    //private EventRepetition eventRepetition???

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int isRepeat() {
        return isRepeat;
    }

    public void setRepeat(int repeat) {
        isRepeat = repeat;
    }
}
