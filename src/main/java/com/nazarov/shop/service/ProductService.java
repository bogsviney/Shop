package com.nazarov.shop.service;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.dao.jdbc.JDBCProductDao;
import com.nazarov.shop.entity.Product;

import java.util.*;

public class ProductService {


    ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public List<Product> findAll(){
        List <Product> products = productDao.findAll();
        System.out.println("Obtain products: " + products.size());
        return products;
    }






}
