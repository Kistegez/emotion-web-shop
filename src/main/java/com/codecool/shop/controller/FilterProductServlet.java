package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
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


@WebServlet(urlPatterns = {"/api/product"})
public class FilterProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String categoryId = req.getParameter("categoryId");
        String supplierId = req.getParameter("supplierId");
        //ProductDao productDataStore = ProductDaoMem.getInstance();
        //ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        //SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        //ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
        ShopDatabaseManager database = new ShopDatabaseManager();
        try {
            database.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductService productService = new ProductService(database.getProductDao(), database.getProductCategoryDao(), database.getSupplierDao());
        List<Product>filteredList = new ArrayList<>();
        List<Product>filteredProductsByCategory =  productService.getProductsForCategory(Integer.parseInt(categoryId));
        List<Product>filteredProductsBySupplier =  productService.getProductsForSupplier(Integer.parseInt(supplierId));
        filteredProductsByCategory.stream()
                .filter(element -> filteredProductsBySupplier.contains(element))
                .collect(Collectors.toList());

            //List<Product> filteredProducts = productService.getFilteredProductsById(categoryId, supplierId);
        Gson gson = new Gson();
        String json = gson.toJson(filteredProductsByCategory);
        out.println(json);
    }

}
