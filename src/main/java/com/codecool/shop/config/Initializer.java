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
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phone = new ProductCategory("Phone", "Hardware", "A phone, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(phone);

        //setting up products and printing it
        productDataStore.add(new Product("Fear", new BigDecimal("9.9"), "USD", "Feel intensive fear for an hour.", phone, amazon));
        productDataStore.add(new Product("Narcism", new BigDecimal("79.9"), "USD", "Feel you are the best for an hour.", tablet, lenovo));
        productDataStore.add(new Product("Amusement", new BigDecimal("12.9"), "USD", "Be frivolous for an hour.", tablet, amazon));
        productDataStore.add(new Product("Drunkeness", new BigDecimal("24.9"), "USD", "Get drunk for an hour whitout hangover and health problems.", tablet, amazon));
        productDataStore.add(new Product("Active", new BigDecimal("13.9"), "USD", "Get the power for a whiile.", tablet, amazon));
        productDataStore.add(new Product("Get cold", new BigDecimal("9.9"), "USD", "Feel the cold for an hour.", tablet, amazon));
        productDataStore.add(new Product("Coma", new BigDecimal("19.9"), "USD", "Fall asleep instant for 2 hour, nothing can wake up you for a while.", tablet, amazon));
        productDataStore.add(new Product("Jerk", new BigDecimal("9.9"), "USD", "Say what you want and do what you want. No inhibition for an hour.", tablet, amazon));
        productDataStore.add(new Product("Average", new BigDecimal("9.9"), "USD", "Feel like just an average random peapole.", tablet, amazon));
        productDataStore.add(new Product("Rich", new BigDecimal("3.9"), "USD", "Just spend your money you feel rich now (for an hour).", tablet, amazon));
        productDataStore.add(new Product("Feminine", new BigDecimal("20"), "USD", "Feel like a pretty women for a day.", tablet, amazon));
        productDataStore.add(new Product("Green", new BigDecimal("3.9"), "USD", "Feel something for the nature. (4 hour)", tablet, amazon));
        productDataStore.add(new Product("Anxiety", new BigDecimal("13.9"), "USD", "Feel inertia and despair for a day.", tablet, amazon));
    }
}
