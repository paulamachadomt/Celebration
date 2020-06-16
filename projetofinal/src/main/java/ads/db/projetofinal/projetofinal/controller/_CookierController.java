package ads.db.projetofinal.projetofinal.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class _CookierController {

    // using @CookieValue to reand an cookie
    @GetMapping("/readACookie")
    public String readCookie(@CookieValue(value = "username", defaultValue = "default") String username) {

        System.out.println("Hey! My username is " + username);

        return "Hey! My username is " + username;
    }

    // setting an cookie value
    @GetMapping("/SetACookie")
    public String setCookie(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("username", "Rafael");
        // set cookie expiration time (is not really necessarily)
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        // set secure encrypt, but cannot be transmitted over uncrypted http (is not
        // really necessarily)
        cookie.setSecure(true);
        // tell the browser that just server can access this cookie (is not really
        // necessarily)
        cookie.setHttpOnly(true);
        // normaly cookies just returning to serve by the same path used to set, but this one can change the scope
        cookie.setPath("/"); // global cookie accessible every where
        // add cookie to response
        // DELETE : to delete an cookie just set the age to 0, and cookie dies
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "Username is changed!";
    }

    // reading all cookie values with HttpServletRequest request;
    @GetMapping("/readAllCookies")
    public String readAllCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies).map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }
        return "No cookies";
    }

}