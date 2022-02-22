package com.codecool.shop.dao.database;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.CartProduct;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class CartDaoJdbc implements CartDao {
    private final DataSource dataSource;

    public CartDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product, int amount) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id, product_id, amount) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, );
            statement.setInt(2, product.getId());
            statement.setInt(3, 1);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new product",e);
        }

    }

    @Override
    public void edit(int value, int id) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public BigDecimal getTotalCartPrice() {
        return null;
    }

    @Override
    public List<CartProduct> getAll() {
        return null;
    }
}
