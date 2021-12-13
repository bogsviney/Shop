package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



public class EditProductsServlet extends HttpServlet {
    private ProductService productService;

    public EditProductsServlet() {
    }

    public EditProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Edit product with id: " + id);
        Product productToEdit = productService.findById(id);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("product", productToEdit);
        String page = pageGenerator.getPage("products_edit.html", parameters);
        response.getWriter().write(page);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product productToEdit = getProductFromRequest(request);
        productService.edit(productToEdit);
        response.sendRedirect("/products");
    }


    int extractProductId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        System.out.println("Path info: " + pathInfo);

        int index = pathInfo.lastIndexOf("/");
        int id = Integer.parseInt(pathInfo.substring(index + 1));
        return id;
    }

    private Product getProductFromRequest(HttpServletRequest request){
        return Product.builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .description(request.getParameter("description"))
                .build();
    }

}





