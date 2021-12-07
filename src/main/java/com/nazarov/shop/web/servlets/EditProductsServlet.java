//package com.nazarov.shop.web.servlets;
//
//import com.nazarov.shop.entity.Product;
//import com.nazarov.shop.service.ProductService;
//import com.nazarov.shop.web.utils.PageGenerator;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class EditProductsServlet extends HttpServlet {
//    private ProductService productService;
//
//    public EditProductsServlet(ProductService productService) {
//        this.productService = productService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PageGenerator pageGenerator = PageGenerator.instance();
//        String page = pageGenerator.getPage("products_edit.html");
//        response.getWriter().write(page);
//    }
//
//
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////    Product product = new Product();
////
////    product.setId(Integer.parseInt(request.getParameter("id")));
////        product.setName(request.getParameter("name"));
////        product.setPrice(Double.valueOf(request.getParameter("price")));
////        product.setDescription(request.getParameter("description"));
////
////        productService.edit(product);
////        response.sendRedirect("/products");
////
////
////    }
//
//
//}
//
//
//
//
//
