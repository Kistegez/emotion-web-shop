package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<String, Integer> products = new HashMap<String, Integer>();

    public void add(int id){
        Product product = ProductDaoMem.getInstance().find(id);
        String name = product.getName();
            int quantity = products.getOrDefault(name, 0) + 1;
            products.put(name, quantity);
        System.out.println(products);
    }
}
