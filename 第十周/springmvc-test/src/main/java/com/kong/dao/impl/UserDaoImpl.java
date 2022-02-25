package com.kong.dao.impl;

import com.kong.dao.UserDao;
import com.kong.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query("select * from test.sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {

        User user = jdbcTemplate.queryForObject("select * from test.user where username=? and password = ?",new BeanPropertyRowMapper<User>(User.class),username,password);
        return user;

    }

    @Override
    public void reg(User user) {
        int row = jdbcTemplate.update("insert into test.user values (?,?,?)",user.getAge(),user.getUsername(),user.getPassword());
        System.out.println(row);
    }
}
