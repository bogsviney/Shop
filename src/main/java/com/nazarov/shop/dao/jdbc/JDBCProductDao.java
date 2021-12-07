package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.dao.jdbc.mapper.ProductRowMapper;
import com.nazarov.shop.entity.Product;

import java.sql.*;
import java.util.*;

public class JDBCProductDao implements ProductDao {

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, price, description, date FROM products;";
    private static final String ADD_SQL = """
            INSERT INTO products (name, price, description,date)
            VALUES(?, ?, ?, ?);
            """;
    private static final String EDIT_SQL = "UPDATE products SET name=?, price=?, description=? WHERE id=?;";

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
    public void edit(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_SQL)) {
            preparedStatement.setDouble(4, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with product updating", e);
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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sqrt");
    }
}
