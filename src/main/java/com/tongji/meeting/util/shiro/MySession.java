package com.tongji.meeting.util.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class MySession implements AuthenticationToken {

    private String token;

    public MySession(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
