package com.codecool.shop.controller;


import com.codecool.shop.dao.memory.CartDaoMem;
import com.codecool.shop.model.CartProduct;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/api/review_cart"})
public class ReviewCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        List<CartProduct> cartProducts = CartDaoMem.getInstance().getAll();
        String cartProductsToJson = new Gson().toJson(cartProducts);
        out.println(cartProductsToJson);

    }
}
