package com.codecool.shop.controller;

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

@WebServlet(urlPatterns = {"/api/login"})
public class UserLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userTemplate = new Gson().fromJson(request.getReader(), User.class);
        String name = userTemplate.getName();
        String password = userTemplate.getPassword();

        UserService userService = UserService.getInstance();

        Gson gson = new Gson();
        String json = gson.toJson(userService.find(name, password));

        userService.find(name, password);

        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
    }
}