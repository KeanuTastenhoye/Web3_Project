package view;

import domain.db.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePerson extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");

        try {
            getService().deletePerson(userid);
        }
        catch (DbException e) {
        }
        return "Controller?action=PersonOverview";
    }
}
