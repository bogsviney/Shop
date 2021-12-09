package com.nazarov.shop.web.servlets;

import com.nazarov.shop.dao.jdbc.JDBCProductDao;
import com.nazarov.shop.service.ProductService;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

//class DeleteProductServletTest {


//    @Test
//    public void testParseIdFromRequest() {
//        JDBCProductDao jdbcProductDao = new JDBCProductDao();
//
//        ProductService productService = new ProductService(jdbcProductDao);
//
//        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService);
//
//        String path = "http://localhost:8080/products/delete/1";
//
//        int actual = deleteProductServlet.parseIdFromRequest(request);
//
//        assertEquals(1, actual);
//
//
//    }
//
//
//}