package ads.db.projetofinal.projetofinal.controller.util;

import javax.servlet.http.Cookie;

public class Util {
        
    public void log(String textLog) {
        System.out.println("-------------------------------------------------------\n" + textLog);
    }

    public static Cookie getCookie(String cookieName, String valueName) {
        Cookie cookie = new Cookie(cookieName, valueName);
        cookie.setMaxAge(1 * 60 * 60);
        cookie.setPath("/");
        return cookie;
    }
    
    public static Cookie killCookie(String cookieName, String valueName) {
        Cookie cookie = new Cookie(cookieName, valueName);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        return cookie;
    }
}