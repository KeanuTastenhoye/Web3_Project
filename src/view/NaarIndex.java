package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NaarIndex extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String cookieQuote = giveCookieWithName(request, "quote");
        request.setAttribute("quote", cookieQuote);
        return "index.jsp";
    }
}
