package domain.db;

import domain.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDBSQL implements ProductDB {
    private Properties properties = new Properties();
    private String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX33?currentSchema=r0667956";

    public ProductDBSQL() {
        properties.setProperty("user", "r0667956");
        properties.setProperty("password", "Ghia2016");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Product product) {
        if (product == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "INSERT INTO product (productId, name, description, price)"
                   + "VALUES ('"
                   + product.getProductId() + "', '"
                   + product.getName() + "', '"
                   + product.getDescription() + "', '"
                   + product.getPrice() + ")";

        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "UPDATE product SET productId, name, description, price WHERE productId = " + product.getProductId();

        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }

    }

    @Override
    public void delete(int productId) {
        String sql = "DELETE FROM product WHERE productId = " + productId;

        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Product get(int productId) {
        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM product WHERE productId = " + productId);
            String name = result.getString("name");
            String description = result.getString("description");
            double price = Double.parseDouble(result.getString("price"));
            Product product = new Product(productId, name, description, price);
            return product;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> records = new ArrayList<Product>();
        try (Connection connection = DriverManager.getConnection(url, properties); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM product");
            while (result.next()) {
                int productId = Integer.parseInt(result.getString("productId"));
                String name = result.getString("name");
                String description = result.getString("description");
                Double price = Double.parseDouble(result.getString("price"));
                Product product = new Product(productId, name, description, price);
                records.add(product);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return records;
    }

    @Override
    public int getNumberOfProducts() {
        List<Product> producten = new ArrayList<Product>();
        producten = getAll();
        return producten.size();
    }
}
