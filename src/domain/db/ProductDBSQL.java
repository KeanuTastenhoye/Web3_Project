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
    public void add(Product product) { //Elk item krijgt 0 als id --> error
        if (product == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "INSERT INTO product (\"productId\", name, description, price)" +
                     "VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new DbException("Nothing to add");
        }

        String sql = "UPDATE product " +
                     "SET name = ?, description = ?, price = ? " +
                     "WHERE \"productId\" = ?";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getProductId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

    }

    @Override
    public void delete(int productId) {
        String sql = "DELETE " +
                     "FROM product " +
                     "WHERE \"productId\" = ?";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Product get(int productId) {
        String sql = "SELECT * " +
                     "FROM product " +
                     "WHERE \"productId\" = ?";

        try (Connection connection = DriverManager.getConnection(url, properties); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");
                Product product = new Product(productId, name, description, price);
                return product;
            }
            else {
                throw new DbException("Failed to get Product");
            }
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
                int productId = result.getInt("productId");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");
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
