package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.memory.CartDaoMem;
import com.codecool.shop.dao.memory.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartProductId = req.getParameter("product_id");
        int intCartProductId = Integer.parseInt(cartProductId);
        if (cartProductId != null) {
            Product product = ProductDaoMem
                    .getInstance()
                    .find(Integer.parseInt(cartProductId));
            CartDao cart = CartDaoMem.getInstance();
            if (cart.find(product.getId()) == null) {
                cart.add(product, 1);
            }
            else {
                cart.edit(1, intCartProductId);
            }
        }
    }
}
