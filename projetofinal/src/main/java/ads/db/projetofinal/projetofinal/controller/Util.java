package ads.db.projetofinal.projetofinal.controller;

import javax.servlet.http.Cookie;

public class Util {
        
    void log(String textLog) {
        System.out.println("-------------------------------------------------------\n" + textLog);
    }

    static Cookie getCookie(String cookieName, String valueName) {
        Cookie cookie = new Cookie(cookieName, valueName);
        cookie.setMaxAge(1 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }
    
    static Cookie killCookie(String cookieName, String valueName) {
        Cookie cookie = new Cookie(cookieName, valueName);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }
}