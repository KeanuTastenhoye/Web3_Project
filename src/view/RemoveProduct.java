package view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.DbException;

public class RemoveProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        try {
            request.setAttribute("product", getService().getProduct(Integer.parseInt(productId)));
            return "deleteProduct.jsp";
        }
        catch (DbException e) {
            return "Controller?action=ProductOverview";
        }
    }

}
