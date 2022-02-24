package com.codecool.shop.dao.database;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.CartProduct;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.databaseModel.CartProductModel;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJdbc{
    private final DataSource dataSource;
    private final ProductDao productDao;

    public CartDaoJdbc(DataSource dataSource, ProductDao productDao) {
        this.dataSource = dataSource;
        this.productDao = productDao;
    }


    public void add(CartProduct product, int id) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id, product_id, amount) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.setInt(2, product.getId());
            statement.setInt(3, product.getAmount());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new product",e);
        }

    }

    public void edit(List<CartProduct> products, int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding one supplier", e);
        }
        for (CartProduct oneProduct : products){
            add(oneProduct, id);
        }

    }


    public List<CartProduct> getAll(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, user_id, product_id, amount FROM cart WHERE user_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            List<CartProduct> result = new ArrayList<>();
            while (rs.next()) {
                CartProduct oneCartProduct = new CartProduct(new Product(productDao.find(rs.getInt(3))), rs.getInt(4));
                oneCartProduct.setId(rs.getInt(3));
                result.add(oneCartProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all the products", e);
        }
    }
}
