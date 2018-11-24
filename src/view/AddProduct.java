package view;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Product;
import domain.model.DomainException;

import domain.db.DbException;

public class AddProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
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
                getService().addProduct(pr);
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
            request.setAttribute("productId", productId);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("price", price);
            return "Controller?action=naarAddProduct";
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

}
