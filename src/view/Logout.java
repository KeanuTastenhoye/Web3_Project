package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("person");
        request.getSession().removeAttribute("role");
        return "Controller?action=NaarIndex";
    }
}
