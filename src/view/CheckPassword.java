package view;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckPassword extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        try{
            Person pe = getService().getPerson(userid);
            request.setAttribute("userid", userid);
            return "checkPassword.jsp";
        }catch(DbException e){
            return "Controller?action=PersonOverview";
        }
    }
}
