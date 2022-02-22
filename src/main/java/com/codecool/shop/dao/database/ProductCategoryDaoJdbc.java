package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    private final DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_category (name, department, description) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDepartment());
            statement.setString(3, category.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new productCategory",e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM product_category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            ProductCategory productCategory = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
            return productCategory;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding the productCategory", e);
        }
    }

    @Override
    public void remove(int id) {

    }

    /*@Override
    public void remove(int id) {

    }*/

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, department, description FROM product_category";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> result = new ArrayList<>();
            while (rs.next()) {
                ProductCategory oneProductCategory = new ProductCategory(rs.getString(2), rs.getString(3), rs.getString(4));
                oneProductCategory.setId(rs.getInt(1));
                result.add(oneProductCategory);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all the products", e);
        }
    }
}
