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
        CartProduct product = this.cartList.stream()
                .filter(cartProduct -> cartProduct.getId() == id)
                .findFirst().get();
        if (product.getAmount() == 1 && value == -1){
            remove(id);
        }
        else {
            int quantity = product.getAmount();
            product.setAmount(quantity + value);
        }
    }

    @Override
    public Product find(int id) {
        CartProduct product = this.cartList.stream()
                .filter(cartProduct -> cartProduct.getId() == id)
                .findAny()
                .orElse(null);
        return product;
    }

    @Override
    public void remove(int id) {
        CartProduct product = this.cartList.stream()
                .filter(cartProduct -> cartProduct.getId() == id)
                .findFirst().get();
        cartList.remove(product);

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
