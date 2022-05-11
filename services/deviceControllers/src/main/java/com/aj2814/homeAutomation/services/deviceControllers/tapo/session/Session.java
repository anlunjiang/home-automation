package com.aj2814.homeAutomation.services.deviceControllers.tapo.session;

import com.aj2814.homeAutomation.services.deviceControllers.common.C658a;

/**
 * Session object containing token for authenticated requests, registered
 * session id (cookie) and the C658a encryptor
 */
public class Session {
    private final String token;
    private final String cookie;
    private final C658a c658a;

    public Session(String token, String cookie, C658a c658a) {
        this.token = token;
        this.cookie = cookie;
        this.c658a = c658a;
    }

    public String getToken() {
        return token;
    }

    public String getCookie() {
        return cookie;
    }

    public C658a getC658a() {
        return c658a;
    }
}
