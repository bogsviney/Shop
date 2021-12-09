package com.nazarov.shop.dao;

import com.nazarov.shop.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

     Product  findById(int id);


    void add(Product product);

    void edit(int id);

    void delete(int id);
}
