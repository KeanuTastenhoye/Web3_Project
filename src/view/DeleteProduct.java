package view;

import domain.db.DbException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");

        try {
            getService().deleteProduct(Integer.parseInt(productId));
        }
        catch (NumberFormatException e) {
        }
        catch (DbException e) {
        }
        return "Controller?action=ProductOverview";
    }
}
