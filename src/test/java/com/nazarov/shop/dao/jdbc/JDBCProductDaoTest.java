package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCProductDaoTest {
    @Test
    public void testFindAllReturnCorrectData() {
        JDBCProductDao jdbcProductDao = new JDBCProductDao();
        List<Product> products = jdbcProductDao.findAll();

        assertFalse((products.isEmpty()));

        for (Product product : products) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getPrice());
            assertNotNull(product.getDescription());
            assertNotNull(product.getPublishDate());
        }

    }

}