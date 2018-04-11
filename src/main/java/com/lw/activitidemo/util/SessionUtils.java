package com.lw.activitidemo.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionUtils {

    private static HttpSession session;
    private  SessionUtils(){
    }

    public static HttpSession getSession() {

        return session;
    }

    public static void setSession(HttpSession aa) {
        session = aa;
    }

}
