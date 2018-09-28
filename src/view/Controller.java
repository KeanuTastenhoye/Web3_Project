package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import domain.db.DbException;
import domain.model.ShopService;
import domain.model.Person;

import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ShopService service;

    public Controller() {
        super();
        service = new ShopService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch(action) {
            case "personOverview":
                personOverview(request, response);
            break;

            case "addPerson":
                addPerson(request, response);
            break;

            case "naarSignUp":
                naarSignUp(request, response);
            break;

            default:
                naarIndex(request, response);
            break;
        }
    }

    private void naarIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void naarSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
    }

    private void personOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("persons", service.getPersons());
        request.getRequestDispatcher("personoverview.jsp").forward(request, response);
    }

    private void addPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Person pe = new Person();
        List<String> errors = new ArrayList<>();

        this.setUserid(errors, pe, userid);
        this.setFirstName(errors, pe, firstName);
        this.setLastName(errors, pe, lastName);
        this.setEmail(errors, pe, email);
        this.setPassword(errors, pe, password);

        try {
            if (errors.isEmpty()) {
                service.addPerson(pe);
            }
        }
        catch (DbException e) {
            errors.add(e.getMessage());
        }

        if (errors.isEmpty()) {
            personOverview(request, response);
        }
        else {
            request.setAttribute("errors", errors);
            request.setAttribute("userid", userid);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            naarSignUp(request, response);
        }
    }

    private void setUserid (List<String> errors, Person pe, String userid) {
        try {
            pe.setUserid(userid);
        }
        catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setFirstName (List<String> errors, Person pe, String firstName) {
        try {
            pe.setFirstName(firstName);
        }
        catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setLastName (List<String> errors, Person pe, String lastName) {
        try {
            pe.setLastName(lastName);
        }
        catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setEmail (List<String> errors, Person pe, String email) {
        try {
            pe.setEmail(email);
        }
        catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

    private void setPassword (List<String> errors, Person pe, String password) {
        try {
            pe.setPassword(password);
        }
        catch (IllegalArgumentException e) {
            errors.add(e.getMessage());
        }
    }

}
