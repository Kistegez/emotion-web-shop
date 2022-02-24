package com.codecool.shop.dao.database;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    private final DataSource dataSource;
    private final SupplierDaoJdbc supplierDaoJdbc;
    private final ProductCategoryDaoJdbc productCategoryDaoJdbc;

    public ProductDaoJdbc(DataSource dataSource, ProductCategoryDaoJdbc productCategoryDao,  SupplierDaoJdbc supplierDao) {
        this.dataSource = dataSource;
        this.productCategoryDaoJdbc = productCategoryDao;
        this.supplierDaoJdbc = supplierDao;
    }


    @Override
    public void add(Product product) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product (name, price, description, supplier_id, product_category_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setFloat(2, Float.parseFloat(product.getPrice()));
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getSupplier().getId());
            statement.setInt(5, product.getProductCategory().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            product.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new product",e);
        }
    }

    @Override
    public Product find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, description, supplier_id, product_category_id FROM product WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            return new Product(rs.getString(2), BigDecimal.valueOf(rs.getFloat(3)), "USD", rs.getString(4), productCategoryDaoJdbc.find(rs.getInt(6)),  supplierDaoJdbc.find(rs.getInt(5)));
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding one product", e);
        }
    }

    @Override
    public void remove(int id) {

    }

    /*@Override
    public void remove(int id) {

    }*/

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, description, supplier_id, product_category_id FROM product";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product oneProduct = new Product(rs.getString(2),
                        BigDecimal.valueOf(rs.getFloat(3)),
                        "USD", rs.getString(4),
                        productCategoryDaoJdbc.find(rs.getInt(6)),
                        supplierDaoJdbc.find(rs.getInt(5)));
                oneProduct.setId(rs.getInt(1));
                result.add(oneProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all the products", e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, description, supplier_id, product_category_id FROM product WHERE supplier_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, supplier.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product oneProduct = new Product(rs.getString(2), BigDecimal.valueOf(rs.getFloat(3)), "USD", rs.getString(4), productCategoryDaoJdbc.find(rs.getInt(6)),  supplierDaoJdbc.find(rs.getInt(5)));
                oneProduct.setId(rs.getInt(1));
                result.add(oneProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding product by supplier", e);
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, price, description, supplier_id, product_category_id FROM product WHERE product_category_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            List<Product> result = new ArrayList<>();
            while (rs.next()) {
                Product oneProduct = new Product(rs.getString(2), BigDecimal.valueOf(rs.getFloat(3)), "USD", rs.getString(4), productCategoryDaoJdbc.find(rs.getInt(6)),  supplierDaoJdbc.find(rs.getInt(5)));
                oneProduct.setId(rs.getInt(1));
                result.add(oneProduct);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding product by productCategory", e);
        }
    }
}
