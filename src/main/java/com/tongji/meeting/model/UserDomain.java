package com.tongji.meeting.model;

public class UserDomain {
    private int userid;
    private String openid;
    private String name;
    private String session_key;
    private String my_session_key;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getMy_session_key() {
        return my_session_key;
    }

    public void setMy_session_key(String my_session_key) {
        this.my_session_key = my_session_key;
    }
}
