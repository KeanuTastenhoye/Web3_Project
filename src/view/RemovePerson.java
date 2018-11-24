package view;

import domain.db.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemovePerson extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        try {
            request.setAttribute("person", getService().getPerson(userid));
            return "deletePerson.jsp";
        }
        catch (DbException e) {
            return "Controller?action=PersonOverview";
        }
    }
}
