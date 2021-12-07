package com.nazarov.shop.service;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.entity.Product;

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

    public void add(Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setPublishDate(now);
        productDao.add(product);
        System.out.println("Product added: " + product);
    }

    public void edit(Product product) {
        productDao.edit(product);
    }


}
