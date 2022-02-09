package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartProduct;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDaoMem implements CartDao {

    private List<CartProduct> cartList = new ArrayList<>();
    private static CartDaoMem instance = null;

    private CartDaoMem() {
    }

    public static CartDaoMem getInstance() {
        if (instance == null) {
            instance = new CartDaoMem();
        }
        return instance;
    }
    @Override
    public void add(Product product, int amount) {
        cartList.add(new CartProduct(product, amount));
    }

    @Override
    public void edit(int value, int id) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public BigDecimal getTotalCartPrice() {
        return null;
    }

    @Override
    public List<CartProduct> getAll() {
        return cartList;
    }
}

