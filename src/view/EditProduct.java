package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Product;

import domain.db.DbException;

public class EditProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");

        try {
            Product pr = getService().getProduct(Integer.parseInt(productId));
            request.setAttribute("product", pr);
            return "updateProduct.jsp";
        }
        catch (DbException e) {
            return "Controller?action=ProductOverview";
        }
    }
}
