package com.nazarov.shop.dao;

import com.nazarov.shop.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    void add(Product product);
}
