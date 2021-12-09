package com.nazarov.shop.service;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.entity.Product;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        List<Product> products = productDao.findAll();
        System.out.println("Obtain products: " + products.size());
        return products;
    }

    public Product findById(int id) {
        Product product = productDao.findById(id);
        return product;
    }

    public void add(Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setPublishDate(now);
        productDao.add(product);
        System.out.println("Product added: " + product);
    }

    public void edit(int id) {
        productDao.edit(id);
    }


    public void delete(int id) {
        productDao.delete(id);
    }


}
