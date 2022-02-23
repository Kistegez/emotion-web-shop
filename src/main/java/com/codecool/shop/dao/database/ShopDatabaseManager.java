package com.codecool.shop.dao.database;

import javax.sql.DataSource;
import java.sql.SQLException;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.service.UserService;
import org.postgresql.ds.PGSimpleDataSource;

public class ShopDatabaseManager {
    private CartDaoJdbc cartDao;
    private ProductDaoJdbc productDao;
    private ProductCategoryDaoJdbc productCategoryDao;
    private SupplierDaoJdbc supplierDao;
    private UserDaoJdbc userDao;


    public void setup() throws SQLException {
        DataSource dataSource = connect();
        cartDao = new CartDaoJdbc(dataSource);
        productCategoryDao = new ProductCategoryDaoJdbc(dataSource);
        supplierDao = new SupplierDaoJdbc(dataSource);
        productDao = new ProductDaoJdbc(dataSource, productCategoryDao, supplierDao);
        UserDaoJdbc userDao = UserDaoJdbc.getInitialInstance(dataSource);
        UserService.createInitialInstance(userDao);


    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = System.getenv("DB_NAME");
        String user = System.getenv("USER_NAME");
        String password = System.getenv("PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

    public ProductDaoJdbc getProductDao() {
        return productDao;
    }

    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    public SupplierDaoJdbc getSupplierDao() {
        return supplierDao;
    }

    public UserDaoJdbc getUserDao() {
        return userDao;
    }
}
