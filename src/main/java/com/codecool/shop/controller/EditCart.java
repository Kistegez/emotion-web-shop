package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.memory.CartDaoMem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/cart/edit"})
public class EditCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String strvalue = req.getParameter("value");
        String strId = req.getParameter("id");
        int value = Integer.parseInt(strvalue);
        int id = Integer.parseInt(strId);

        CartDao cart = CartDaoMem.getInstance();
        cart.edit(value, id);
    }
}