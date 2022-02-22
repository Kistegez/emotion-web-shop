package com.codecool.shop.model.databaseModel;

import com.codecool.shop.model.BaseModel;

import java.math.BigDecimal;

public class ProductModel extends BaseModel {

    private final int supplierId;
    private final int productCategoryId;
    private final String currencyString;
    private final BigDecimal price;


    public ProductModel(String name, String description, float price, String currencyString, int supplierId, int productCategoryId) {
        super(name, description);
        this.supplierId = supplierId;
        this.productCategoryId = productCategoryId;
        this.currencyString = currencyString;
        this.price = new BigDecimal(price);
    }
}
