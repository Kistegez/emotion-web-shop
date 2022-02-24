package com.codecool.shop.controller;


import com.codecool.shop.dao.database.ShopDatabaseManager;
import com.codecool.shop.dao.database.UserDaoJdbc;
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


@WebServlet(urlPatterns = {"/api/registration"})
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShopDatabaseManager database = new ShopDatabaseManager();

        try {
            database.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        User userTemplate = new Gson().fromJson(request.getReader(), User.class);
        String userName = userTemplate.getName();
        String email = userTemplate.getEmail();
        String password = userTemplate.getPassword();
        System.out.println(userName);
        UserService userService = UserService.getInstance();
        userService.add(new User(userName, email, password));



    }

}
