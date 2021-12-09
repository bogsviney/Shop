package com.nazarov.shop.web.servlets;

import com.nazarov.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductServlet extends HttpServlet {
    private ProductService productService;

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int index = pathInfo.lastIndexOf("/");
        int id = Integer.valueOf(pathInfo.substring(index+1, pathInfo.length()));
        productService.delete(id);
        response.sendRedirect("/products");
    }

}
