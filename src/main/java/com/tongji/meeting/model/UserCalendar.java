package com.tongji.meeting.model;

public class UserCalendar {
    private int calendarId;
    private int userId;
    private boolean disturb;
    private String role;
    private boolean detailExposed;
    private UserDomain userDomain;

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isDisturb() {
        return disturb;
    }

    public void setDisturb(boolean disturb) {
        this.disturb = disturb;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDetailExposed() { return detailExposed; }

    public void setDetailExposed(boolean detailExposed) { this.detailExposed = detailExposed; }

}
