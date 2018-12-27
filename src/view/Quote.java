package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Quote extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String choice = request.getParameter("choice");
        Cookie cookie = new Cookie("quote", choice);

        response.addCookie(cookie);

        return "Controller?action=NaarIndex";
    }
}
