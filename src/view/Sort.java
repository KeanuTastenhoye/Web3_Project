package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sort extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String sort = request.getParameter("sort");
        Cookie cookie = new Cookie("sort", sort);

        response.addCookie(cookie);

        return "Controller?action=PersonOverview";
    }
}