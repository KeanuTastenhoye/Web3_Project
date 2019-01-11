package view;

import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

public class AddToCart extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        //int amount = Integer.parseInt(request.getParameter("amount"));

        try {
            Product pr = getService().getProduct(Integer.parseInt(productId));
            request.getSession().setAttribute("product", pr);
            //request.getSession().setAttribute("amount", amount);
            return "Controller?action=CartOverview";
        } catch (DbException e) {
            return "Controller?action=ProductOverview";
        }
    }
}
