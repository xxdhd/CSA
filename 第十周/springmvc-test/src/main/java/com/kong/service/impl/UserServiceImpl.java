package com.kong.service.impl;

import com.kong.dao.UserDao;
import com.kong.domain.User;
import com.kong.service.UserService;
import org.springframework.dao.EmptyResultDataAccessException;


public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = userDao.findByUsernameAndPassword(username,password);
            return user;
        }catch (EmptyResultDataAccessException e){
            return null;
        }


    }

    @Override
    public void reg(User user) {
        userDao.reg(user);
    }
}
