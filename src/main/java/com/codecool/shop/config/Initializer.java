package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier mcDonalds = new Supplier("McDonalds", "Good feelings and strength.");
        supplierDataStore.add(mcDonalds);
        Supplier unicum = new Supplier("Unicum", "Average feelings.");
        supplierDataStore.add(unicum);
        Supplier gucci = new Supplier("Gucci", "Narcist feelings.");
        supplierDataStore.add(gucci);

        //setting up a new product category
        ProductCategory badEmotion = new ProductCategory("Bad", "Emotion", "You lose spirit energy using bad feelings.");
        productCategoryDataStore.add(badEmotion);
        ProductCategory goodEmotion = new ProductCategory("Good", "Emotion", "Good emotions upload your spiritual energy");
        productCategoryDataStore.add(goodEmotion);

        //setting up products and printing it
        productDataStore.add(new Product("Fear", new BigDecimal("9.9"), "USD", "Feel intense fear for an hour.", badEmotion, unicum));
        productDataStore.add(new Product("Narcism", new BigDecimal("79.9"), "USD", "Feel like you are the best for an hour.", goodEmotion, gucci));
        productDataStore.add(new Product("Amusement", new BigDecimal("12.9"), "USD", "Be light-headed for an hour.", goodEmotion, unicum));
        productDataStore.add(new Product("Drunkeness", new BigDecimal("24.9"), "USD", "Get drunk for an hour without hangover and health problems.", goodEmotion,unicum));
        productDataStore.add(new Product("Active", new BigDecimal("13.9"), "USD", "Get the power for a while.", goodEmotion, mcDonalds));
        productDataStore.add(new Product("Feel cold", new BigDecimal("9.9"), "USD", "Feel the cold for an hour.", badEmotion, unicum));
        productDataStore.add(new Product("Coma", new BigDecimal("19.9"), "USD", "Fall asleep instant for 2 hour, nothing can wake up you for a while.", goodEmotion, unicum));
        productDataStore.add(new Product("Jerk", new BigDecimal("9.9"), "USD", "Say what you want and do what you want. No inhibition for an hour.", goodEmotion, gucci));
        productDataStore.add(new Product("Average", new BigDecimal("9.9"), "USD", "Feel like just an average random people.", badEmotion, mcDonalds));
        productDataStore.add(new Product("Rich", new BigDecimal("3.9"), "USD", "Feel like rich person and spend your money without gilt (for an hour).", goodEmotion, gucci));
        productDataStore.add(new Product("Feminine", new BigDecimal("20"), "USD", "Feel like a pretty women for a day.", badEmotion, gucci));
        productDataStore.add(new Product("Green", new BigDecimal("3.9"), "USD", "Feel like you carry for the nature. (4 hour)", badEmotion, mcDonalds));
        productDataStore.add(new Product("Anxiety", new BigDecimal("13.9"), "USD", "Feel inertia and despair for a day.", badEmotion, mcDonalds));
    }
}
