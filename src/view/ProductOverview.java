package view;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Product;

public class ProductOverview extends RequestHandler{

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Product> records  = getService().getProducts();
        request.setAttribute("records", records);
        return "productoverview.jsp";
    }
}
