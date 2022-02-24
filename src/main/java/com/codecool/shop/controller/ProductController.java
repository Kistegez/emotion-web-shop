package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SetUpMemOrJdbc;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.database.ShopDatabaseManager;
import com.codecool.shop.dao.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.memory.ProductDaoMem;
import com.codecool.shop.dao.memory.SupplierDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    ProductDao productDataStore;
    ProductCategoryDao productCategoryDataStore;
    SupplierDao supplierDataStore;
    SetUpMemOrJdbc setUpMemOrJdbc = new SetUpMemOrJdbc();
    boolean daoTypeIsJdbc;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", productService.getAllProducts());
        context.setVariable("categories", productService.getAllCategories());
        context.setVariable("suppliers", productService.getAllSuppliers());
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);

        engine.process("product/index.html", context, resp.getWriter());
    }
}
