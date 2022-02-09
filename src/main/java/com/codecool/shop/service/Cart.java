package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;

import java.util.*;

public class Cart {
    List<Product> products = new ArrayList<>();

    public void add(int id){
        Product product = ProductDaoMem.getInstance().find(id);
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
