package view;

import domain.model.DomainException;
import domain.model.Person;
import domain.model.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        Person pe;

        try {
            if (password.matches(".*[<|>].*") || userid.matches(".*[<|>].*")) {
                throw new DomainException();
            }
            pe = getService().getPerson(userid);
            if (!pe.isCorrectPassword(password)) {
                return "Controller?action=NaarIndex";
            } else {
                request.getSession().setAttribute("person", pe.getFirstName());
                if (pe.getRole() == Role.ADMIN) {
                    request.getSession().setAttribute("role",pe.getRole().toString());
                }
            }
        } catch (Exception e) {
            return "Controller?action=NaarIndex";
        }
        return "Controller?action=NaarIndex";
    }
}
