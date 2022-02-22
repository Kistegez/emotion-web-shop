package com.codecool.shop.dao.database;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    private final DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Supplier supplier) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding a new supplier",e);
        }
    }

    @Override
    public Supplier find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            Supplier supplier = new Supplier(rs.getString(2), rs.getString(3));
            return supplier;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding one supplier", e);
        }
    }

    @Override
    public void remove(int id) {

    }

    /*@Override
    public void remove(int id) {

    }*/

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT id, name, description FROM supplier";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> result = new ArrayList<>();
            while (rs.next()) {
                Supplier oneSupplier = new Supplier(rs.getString(2), rs.getString(3));
                oneSupplier.setId(rs.getInt(1));
                result.add(oneSupplier);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding all suppliers", e);
        }
    }
}
