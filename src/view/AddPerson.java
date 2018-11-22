package view;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Person;
import domain.model.ShopService;
import domain.model.DomainException;
import domain.db.DbException;

public class AddPerson extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ShopService service = new ShopService(properties);

        String userid = request.getParameter("userid");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Person pe = new Person();
        List<String> errorsPerson = new ArrayList<>();

        this.setUserid(errorsPerson, pe, userid);
        this.setFirstName(errorsPerson, pe, firstName);
        this.setLastName(errorsPerson, pe, lastName);
        this.setEmail(errorsPerson, pe, email);
        this.setPassword(errorsPerson, pe, password);

        try {
            if (errorsPerson.isEmpty()) {
                service.addPerson(pe);
            }
        }
        catch (DbException e) {
            errorsPerson.add(e.getMessage());
        }

        if (errorsPerson.isEmpty()) {
            return PersonOverview;
        }
        else {
            request.setAttribute("errorsPerson", errorsPerson);
            request.setAttribute("userid", userid);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            return NaarSignUp;
        }
    }

    private void setUserid (List<String> errorsPerson, Person pe, String userid) {
        try {
            pe.setUserid(userid);
        }
        catch (DomainException e) {
            errorsPerson.add(e.getMessage());
        }
    }

    private void setFirstName (List<String> errorsPerson, Person pe, String firstName) {
        try {
            pe.setFirstName(firstName);
        }
        catch (DomainException e) {
            errorsPerson.add(e.getMessage());
        }
    }

    private void setLastName (List<String> errorsPerson, Person pe, String lastName) {
        try {
            pe.setLastName(lastName);
        }
        catch (DomainException e) {
            errorsPerson.add(e.getMessage());
        }
    }

    private void setEmail (List<String> errorsPerson, Person pe, String email) {
        try {
            pe.setEmail(email);
        }
        catch (DomainException e) {
            errorsPerson.add(e.getMessage());
        }
    }

    private void setPassword (List<String> errorsPerson, Person pe, String password) {
        try {
            pe.setPassword(password);
        }
        catch (DomainException e) {
            errorsPerson.add(e.getMessage());
        }
    }
}
