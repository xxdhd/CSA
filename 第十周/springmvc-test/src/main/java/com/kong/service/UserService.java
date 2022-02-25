package com.kong.service;

import com.kong.domain.User;


public interface UserService {

    User login(String username, String password);

    void reg(User user);

}
