package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Quote extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String quote = request.getParameter("quote");
        Cookie cookieQuote = new Cookie("quote", quote);

        response.addCookie(cookieQuote);

        if (cookieQuote.getValue().equals("Yes")) {
            request.setAttribute("quote", "Yes");
            return "Controller?action=NaarIndex";
        }
        else {
            request.setAttribute("quote", "No");
            return "Controller?action=NaarIndex";
        }
    }

}
