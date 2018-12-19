package view;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sort extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {

        //Werkt niet omdat het een drop down is --> Select --> met radio button geen error

        String option = request.getParameter("option");
        Cookie cookieSort = new Cookie("option", option);

        response.addCookie(cookieSort);

        if (cookieSort.getValue().equals("User Id")) {
            request.setAttribute("option", "User Id");
            return "Controller?action=PersonOverview";
        }
        else if (cookieSort.getValue().equals("Email")) {
            request.setAttribute("option", "Email");
            return "Controller?action=PersonOverview";
        }
        else if (cookieSort.getValue().equals("First Name")) {
            request.setAttribute("option", "First Name");
            return "Controller?action=PersonOverview";
        }
        else if (cookieSort.getValue().equals("Last Name")) {
            request.setAttribute("option", "Last Name");
            return "Controller?action=PersonOverview";
        }
        else {
            return "Controller?action=PersonOverview";
        }
    }
}