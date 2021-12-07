package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class AddProductServlet extends HttpServlet {
    private ProductService productService;

    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("product_add.html");   // а как сделать без хеш мап?
        response.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = getProductFromRequest(request);
            productService.add(product);
            response.sendRedirect("products_list.html");
        } catch (Exception e) {
            String errorMessage = "Product not added! Enter correct data in the fields";
            PageGenerator pageGenerator = PageGenerator.instance();
            Map<String, Object> parameters = Map.of("errorMessage", errorMessage);
            String page = pageGenerator.getPage("product_add", parameters);
            response.getWriter().write(page);
        }
    }


    private Product getProductFromRequest(HttpServletRequest request) {
        return Product.builder()
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .description(request.getParameter("description"))
                .publishDate(LocalDateTime.parse(request.getParameter("date")))
                .build();

    }
}
