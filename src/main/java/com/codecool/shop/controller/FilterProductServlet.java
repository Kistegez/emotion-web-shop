package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SetUpMemOrJdbc;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.database.ShopDatabaseManager;
import com.codecool.shop.dao.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.memory.ProductDaoMem;
import com.codecool.shop.dao.memory.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@WebServlet(urlPatterns = {"/api/product"})
public class FilterProductServlet extends HttpServlet {

    ProductDao productDataStore;
    ProductCategoryDao productCategoryDataStore;
    SupplierDao supplierDataStore;
    SetUpMemOrJdbc setUpMemOrJdbc = new SetUpMemOrJdbc();
    boolean daoTypeIsJdbc;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String categoryId = req.getParameter("categoryId");
        String supplierId = req.getParameter("supplierId");
        this.daoTypeIsJdbc = setUpMemOrJdbc.readResource();
        if(daoTypeIsJdbc){
            ShopDatabaseManager database = new ShopDatabaseManager();
            try {
                database.setup();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            productDataStore = database.getProductDao();
            productCategoryDataStore =  database.getProductCategoryDao();
            supplierDataStore = database.getSupplierDao();
        }else {
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
        }
        ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
        List<Product>filteredProducts =  productService.getFilteredProducts(Integer.parseInt(categoryId), Integer.parseInt(supplierId));
        Gson gson = new Gson();
        String json = gson.toJson(filteredProducts);
        out.println(json);
    }

}
