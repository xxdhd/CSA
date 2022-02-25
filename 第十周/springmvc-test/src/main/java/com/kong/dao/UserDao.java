package com.kong.dao;

import com.kong.domain.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);

    void reg(User user);
}
