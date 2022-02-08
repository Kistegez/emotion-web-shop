package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    int id;
    Map<String, Integer> products = new HashMap<String, Integer>();

    public void add(int id){
        Product product = ProductDaoMem.getInstance().find(id);
        System.out.println(product);
    }
}
