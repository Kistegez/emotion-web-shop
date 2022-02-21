package com.codecool.shop.service;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.math.BigDecimal;

public class CartProduct extends Product {

    private int amount;

    public CartProduct(Product product, int amount) {
        super(product);
        this.amount = amount;
        this.id = product.getId();
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
