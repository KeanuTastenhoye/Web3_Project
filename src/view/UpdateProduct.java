package view;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.DomainException;
import domain.model.Product;

import domain.db.DbException;

public class UpdateProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productId = request.getParameter("productId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            Product pr = getService().getProduct(Integer.parseInt(productId));
            List<String> errorsProduct = new ArrayList<String>();

            this.setName(errorsProduct, pr, name);
            this.setDescription(errorsProduct, pr, description);
            this.setPrice(errorsProduct, pr, price);

            try {
                if (errorsProduct.isEmpty()) {
                    getService().updateProducts(pr);
                }
            }
            catch (DbException e) {
                errorsProduct.add(e.getMessage());
            }

            if (errorsProduct.isEmpty()) {
                return "Controller?action=ProductOverview";
            }
            else {
                request.setAttribute("errorsProduct", errorsProduct);
                request.setAttribute("product", pr);
                return "Controller?action=EditProduct";
            }
        }
        catch (DbException e) {
            return "Controller?action=EditProduct";
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

}

