package view;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class CartOverview extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        /*List<Product> products = new ArrayList<>();
        products.add((Product)request.getSession().getAttribute("product"));
        System.out.println(products);*/
        return "cartoverview.jsp";
    }
}
