package com.kong.dao;

import com.kong.pojo.Account;

import java.util.List;

public interface AccountDao {
    //获取全部用户
    List<Account> getAccountList();
    //根据id查询
    Account getAccountById(String id);

    // 新建用户
    int addAccount(Account acc);

    //修改用户
    int updateAccount(Account user);

    //删除用户
    int delAcc(String id);
}
