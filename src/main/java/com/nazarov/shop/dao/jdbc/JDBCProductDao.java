package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.dao.jdbc.mapper.ProductRowMapper;
import com.nazarov.shop.entity.Product;

import java.sql.*;
import java.util.*;

public class JDBCProductDao implements ProductDao {

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();

    private static final String FIND_ALL_SQL = "SELECT id, name, price, description, date FROM products;";


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

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "sqrt");

    }
}
