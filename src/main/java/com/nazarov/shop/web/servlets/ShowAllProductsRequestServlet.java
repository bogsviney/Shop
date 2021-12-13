package com.nazarov.shop.web.servlets;


import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ShowAllProductsRequestServlet extends HttpServlet {

    private ProductService productService;

    public ShowAllProductsRequestServlet(ProductService productService) {
        this.productService = productService;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        List<Product> products = productService.findAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products",products);
        String page = pageGenerator.getPage("products_list.html", parameters);
        resp.getWriter().write(page);
    }

}
