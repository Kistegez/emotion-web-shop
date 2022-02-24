package com.codecool.shop.model.databaseModel;

public class CartProductModel {
    private int id;
    private int userId;
    private int productId;
    private int amount;

    public CartProductModel(int id, int userId, int productId, int amount) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getAmount() {
        return amount;
    }
}
