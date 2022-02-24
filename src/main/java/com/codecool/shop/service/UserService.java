package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

public class UserService {
    private UserDao userDao;
    private static UserService instance = null;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserService createInitialInstance(UserDao userDao) {
        instance = new UserService(userDao);
        return instance;
    }

    public static UserService getInstance() {
        return instance;
    }

    public void add(User user) {
        userDao.add(user);
    }

    public User find(String name, String password){
        return userDao.find(name, password);
    }
}




