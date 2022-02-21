package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartProduct;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public interface CartDao {

    void add(Product product, int amount);
    void edit(int value, int id);
    Product find(int id);
    void remove(int id);
    BigDecimal getTotalCartPrice();
    List<CartProduct> getAll();
}
