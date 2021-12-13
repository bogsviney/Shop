package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.dao.jdbc.mapper.ProductRowMapper;
import com.nazarov.shop.entity.Product;

import java.sql.*;
import java.util.*;

public class JDBCProductDao implements ProductDao {

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, price, description, date FROM products;";
    private static final String FIND_BY_ID_SQL = "SELECT id, name, price, description, date FROM products WHERE id =?;";
    private static final String ADD_SQL = """
            INSERT INTO products (name, price, description,date)
            VALUES(?, ?, ?, ?);
            """;
    private static final String EDIT_SQL = "UPDATE products SET name=?, price=?, description=? WHERE id=?;";
    private static final String DELETE_SQL = "DELETE FROM products WHERE id = ?;";

    List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            return product;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void edit(Product newProduct) {
//        for (Product product : products) {
//            if (product.getId() == newProduct.getId()) {
//                product.setName(newProduct.getName());
//                product.setPrice(newProduct.getPrice());
//                product.setDescription(newProduct.getDescription());
//            }
//        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_SQL)) {
            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setDouble(2, newProduct.getPrice());
            preparedStatement.setString(3, newProduct.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with product editing", e);
        }
    }


    @Override
    public void add(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(product.getPublishDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with product adding", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with product deleting", e);
        }

    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sqrt");
    }
}
