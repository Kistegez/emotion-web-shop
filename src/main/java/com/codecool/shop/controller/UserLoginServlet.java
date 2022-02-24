package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.database.ShopDatabaseManager;
import com.codecool.shop.dao.memory.CartDaoMem;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/api/login"})
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userTemplate = new Gson().fromJson(request.getReader(), User.class);
        String name = userTemplate.getName();
        String password = userTemplate.getPassword();
        UserService userService = UserService.getInstance();
        Gson gson = new Gson();

        User user = userService.find(name, password);
        int userId = user.getId();
        ShopDatabaseManager shopDatabaseManager = new ShopDatabaseManager();
        try {
            shopDatabaseManager.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(userId != 0) {
            List<CartProduct> cartProducts = shopDatabaseManager.getCartDao().getAll(userId);
            for(CartProduct oneCartProduct : cartProducts){
                CartDaoMem.getInstance().add(oneCartProduct, oneCartProduct.getAmount());
            }
        }

        String json = gson.toJson(user);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
    }
}