package view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Person;

public class PersonOverview extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons  = getService().getPersons();
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }

    public List<Person> getPersons() {
        List<Person> persons  = getService().getPersons();
        return persons;
    }

}
