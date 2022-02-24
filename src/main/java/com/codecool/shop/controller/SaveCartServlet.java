package com.codecool.shop.controller;


import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.database.CartDaoJdbc;
import com.codecool.shop.dao.database.ShopDatabaseManager;
import com.codecool.shop.dao.memory.CartDaoMem;
import com.codecool.shop.dao.memory.ProductDaoMem;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/saveCart"})
public class SaveCartServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String userId = req.getParameter("user_id");

        List<CartProduct> cartProducts = CartDaoMem.getInstance().getAll();
        ShopDatabaseManager shopDatabaseManager = new ShopDatabaseManager();
        try {
            shopDatabaseManager.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        shopDatabaseManager.getCartDao().edit(cartProducts, Integer.parseInt(userId));
    }
}
