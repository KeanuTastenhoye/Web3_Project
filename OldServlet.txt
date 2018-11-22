package view;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.ShopService;
import domain.model.Person;
import domain.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ShopService service;

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();
        Properties properties = new Properties();

        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("currentSchema", context.getInitParameter("currentSchema"));
        properties.setProperty("url", context.getInitParameter("url"));

        service = new ShopService(properties);
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

            case "productOverview":
                productOverview(request, response);
            break;

            case "addPerson":
                addPerson(request, response);
            break;

            case "addProduct":
                addProduct(request, response);
            break;

            case "naarSignUp":
                naarSignUp(request, response);
            break;

            case "naarAddProduct":
                naarAddProduct(request, response);
            break;

            case "editProduct":
                editProduct(request, response);
            break;

            case "updateProduct":
                updateProduct(request, response);
            break;

            case "removeProduct":
                removeProduct(request, response);
            break;

            case "deleteProduct":
                deleteProduct(request, response);
            break;

            case "removePerson":
                removePerson(request, response);
            break;

            case "deletePerson":
                deletePerson(request, response);
            break;

            case "checkPassword":
                checkPassword(request, response);
            break;

            case "verifyPassword":
                verifyPassword(request, response);
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

    private void naarAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
    }

    private void personOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("persons", service.getPersons());
        request.getRequestDispatcher("personoverview.jsp").forward(request, response);
    }

    private void productOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("records", service.getProducts());
        request.getRequestDispatcher("productoverview.jsp").forward(request, response);
    }

    private void addPerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            personOverview(request, response);
        }
        else {
            request.setAttribute("errorsPerson", errorsPerson);
            request.setAttribute("userid", userid);
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("email", email);
            naarSignUp(request, response);
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

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        Product pr = new Product();
        List<String> errorsProduct = new ArrayList<>();

        this.setProductId(errorsProduct, pr, productId);
        this.setName(errorsProduct, pr, name);
        this.setDescription(errorsProduct, pr, description);
        this.setPrice(errorsProduct, pr, price);

        try {
            if (errorsProduct.isEmpty()) {
                service.addProduct(pr);
            }
        }
        catch (DbException e) {
            errorsProduct.add(e.getMessage());
        }

        if (errorsProduct.isEmpty()) {
            productOverview(request, response);
        }
        else {
            request.setAttribute("errorsProduct", errorsProduct);
            request.setAttribute("productId", productId);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("price", price);
            naarAddProduct(request, response);
        }
    }

    private void setProductId (List<String> errorsProduct, Product pr, int productId) {
        try {
            pr.setProductId(productId);
        }
        catch (DomainException | NumberFormatException e) {
            errorsProduct.add(e.getMessage());
        }
    }

    private void setName (List<String> errorsProduct, Product pr, String name) {
        try {
            pr.setName(name);
        }
        catch (DomainException e) {
            errorsProduct.add(e.getMessage());
        }
    }

    private void setDescription (List<String> errorsProduct, Product pr, String description) {
        try {
            pr.setDescription(description);
        }
        catch (DomainException e) {
            errorsProduct.add(e.getMessage());
        }
    }

    private void setPrice (List<String> errorsProduct, Product pr, String price) {
        try {
            pr.setPrice(price);
        }
        catch (DomainException e) {
            errorsProduct.add(e.getMessage());
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            Product pr = service.getProduct(Integer.parseInt(productId));
            List<String> errorsProduct = new ArrayList<String>();

            this.setName(errorsProduct, pr, name);
            this.setDescription(errorsProduct, pr, description);
            this.setPrice(errorsProduct, pr, price);

            try {
                if (errorsProduct.isEmpty()) {
                    service.updateProducts(pr);
                }
            }
            catch (DbException e) {
                errorsProduct.add(e.getMessage());
            }

            if (errorsProduct.isEmpty()) {
                productOverview(request, response);
            }
            else {
                request.setAttribute("errorsProduct", errorsProduct);
                request.setAttribute("product", pr);
                editProduct(request, response);
            }
        }
        catch (DbException e) {
            editProduct(request, response);
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");

        try {
            Product pr = service.getProduct(Integer.parseInt(productId));
            request.setAttribute("product", pr);
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
        }
        catch (DbException e) {
            productOverview(request, response);
        }
    }

    private void removeProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        try {
            request.setAttribute("product", service.getProduct(Integer.parseInt(productId)));
            request.getRequestDispatcher("deleteProduct.jsp").forward(request, response);
        }
        catch (DbException e) {
            productOverview(request, response);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");

        try {
            service.deleteProduct(Integer.parseInt(productId));
        }
        catch (NumberFormatException e) {
        }
        catch (DbException e) {
        }
        productOverview(request, response);
    }

    private void removePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        try {
            request.setAttribute("person", service.getPerson(userid));
            request.getRequestDispatcher("deletePerson.jsp").forward(request, response);
        }
        catch (DbException e) {
            personOverview(request, response);
        }
    }

    private void deletePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");

        try {
            service.deletePerson(userid);
        }
        catch (DbException e) {
        }
        personOverview(request, response);
    }

    private void checkPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        try{
            Person pe = service.getPerson(userid);
            request.setAttribute("userid", userid);
            request.getRequestDispatcher("checkPassword.jsp").forward(request, response);
        }catch(DbException e){
            personOverview(request, response);
        }
    }

    private void verifyPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        try{
            Person pe = service.getPerson(userid);
            request.setAttribute("correct", pe.isCorrectPassword(password));
            request.getRequestDispatcher("correctPassword.jsp").forward(request, response);
        }catch(DbException e){
            personOverview(request, response);
        }
    }

}
