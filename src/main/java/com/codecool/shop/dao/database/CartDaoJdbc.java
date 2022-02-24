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
            String sql = "INSERT INTO cart (shop_user_id, product_id, amount) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, 1);
            statement.setInt(2, product.getId());
            statement.setInt(3, product.getAmount());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new product",e);
        }

    }

    public void edit(List<CartProduct> products, int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart WHERE shop_user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, 1);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding one supplier", e);
        }
        for (CartProduct oneProduct : products){
            add(oneProduct, id);
        }

    }


    public List<CartProduct> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, shop_user_id, product_id, amount FROM cart";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<CartProduct> result = new ArrayList<>();
            while (rs.next()) {
                CartProduct oneCartProduct = new CartProduct(new Product(productDao.find(rs.getInt(3))), rs.getInt(4));
                oneCartProduct.setId(rs.getInt(1));
                result.add(oneCartProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all the products", e);
        }
    }
}
