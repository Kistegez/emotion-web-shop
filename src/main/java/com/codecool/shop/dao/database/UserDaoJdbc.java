package com.codecool.shop.dao.database;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;
    private static UserDaoJdbc instance = null;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDaoJdbc getInitialInstance(DataSource dataSource) {
        instance = new UserDaoJdbc(dataSource);

        return instance;
    }


    @Override
    public void add(User user) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO shop_user (user_name, password, email) VALUES(?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public User find(String name, String password) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT id, user_name, email, password FROM shop_user WHERE user_name = ? AND password = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            User user = new User(rs.getString(2),
                    rs.getString(3),
                    rs.getString(4));
            user.setId(rs.getInt(1));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

