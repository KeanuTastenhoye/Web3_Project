package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonOverview extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String sort = "";
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("sort")) {
                    sort = cookie.getValue();
                }
            }
        }
        request.setAttribute("persons", getService().getPersons(sort));
        request.setAttribute("headers", getService().getHeaders());
        request.setAttribute("sort", sort);
        return "personoverview.jsp";
    }
}
