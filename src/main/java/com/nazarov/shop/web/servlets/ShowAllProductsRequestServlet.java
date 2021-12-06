package com.nazarov.shop.web.servlets;


import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAllProductsRequestServlet extends HttpServlet {

    private ProductService productService;

    public ShowAllProductsRequestServlet(ProductService productService) {
        this.productService = productService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        String string = products.toString();
        resp.getWriter().write(string);
    }
}
