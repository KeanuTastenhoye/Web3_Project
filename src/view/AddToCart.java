package view;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class AddToCart extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");

        try {
            Product pr = getService().getProduct(Integer.parseInt(productId));
            request.setAttribute("product", pr);
            return "cartoverview.jsp";
        }
        catch (DbException e) {
            return "Contoller?action=ProductOverview";
        }
    }
}
