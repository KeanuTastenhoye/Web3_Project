package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NaarIndex extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String cooky = "";
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("quote")) {
                    cooky = cookie.getValue();
                }
            }
        }

        if (cooky.equals("Yes")) {
            request.setAttribute("quote", "Greatness From Small Beginnings");
        }
        else {
            request.setAttribute("quote", "");
        }

        return "index.jsp";
    }
}
