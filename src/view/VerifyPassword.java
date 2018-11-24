package view;

import domain.db.DbException;
import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyPassword extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        try{
            Person pe = getService().getPerson(userid);
            request.setAttribute("correct", pe.isCorrectPassword(password));
            return "correctPassword.jsp";
        }catch(DbException e){
            return "Controller?action=PersonOverview";
        }
    }
}
