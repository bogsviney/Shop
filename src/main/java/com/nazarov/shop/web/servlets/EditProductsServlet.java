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


public class EditProductsServlet extends HttpServlet {
    private ProductService productService;

    public EditProductsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products",products);
        String page = pageGenerator.getPage("products_list.html", parameters);
        resp.getWriter().write(page);

    }


//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String pathInfo = request.getPathInfo();
//        int index = pathInfo.lastIndexOf("/");
//        int id = Integer.valueOf(pathInfo.substring(index+1, pathInfo.length()));
//    Product product = productService.findById(id);
//    product.setId(Integer.parseInt(request.getParameter("id")));
//        product.setName(request.getParameter("name"));
//        product.setPrice(Double.valueOf(request.getParameter("price")));
//        product.setDescription(request.getParameter("description"));
//
//        productService.edit(id);
//        response.sendRedirect("/products");
//
//
//    }




}





