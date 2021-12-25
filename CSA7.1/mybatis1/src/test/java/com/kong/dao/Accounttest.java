package com.kong.dao;


import com.kong.pojo.Account;
import com.kong.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Accounttest {
    @Test
    public void test(){
        //获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行方式一
        AccountDao accDao = sqlSession.getMapper(AccountDao.class);

        List<Account> accList =  accDao.getAccountList();

        for(Account acc :accList){
            System.out.println(acc);
        }

        sqlSession.close();
    }
    @Test
    public void testid(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        AccountDao mapper1 =sqlSession.getMapper(AccountDao.class);

        Account acc1 =mapper1.getAccountById("1");

        System.out.println(acc1);

        sqlSession.close();
    }

    @Test
    public void addAcc(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        AccountDao mapper2 =sqlSession.getMapper(AccountDao.class);

        int res = mapper2.addAccount(new Account("1","陈",100,new java.sql.Date((new java.util.Date()).getTime()),new java.sql.Date((new java.util.Date()).getTime())));
        if(res>0){
            System.out.println("插入成功！");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateAcc(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        AccountDao mapper3 = sqlSession.getMapper(AccountDao.class);
        mapper3.updateAccount(new Account("3","狗",1000,new java.sql.Date((new java.util.Date()).getTime()),new java.sql.Date((new java.util.Date()).getTime())));

        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleAcc(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        AccountDao mapper4 =sqlSession.getMapper(AccountDao.class);

        mapper4.delAcc("1");

        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void transfer(){

    SqlSession sqlSession = MybatisUtils.getSqlSession();

    AccountDao mapper1 =sqlSession.getMapper(AccountDao.class);
    int money = 80;
    Account acc1 =mapper1.getAccountById("1");
    Account acc2 =mapper1.getAccountById("2");
    if(acc1.getMoney()>money){
        acc1.setMoney(acc1.getMoney()-money);
        acc2.setMoney(acc2.getMoney()+money);
        mapper1.updateAccount(acc1);
        mapper1.updateAccount(acc2);
        sqlSession.commit();
        System.out.println("转账成功！");
    }
    else{
        System.out.println("余额不足！");
    }
        sqlSession.close();
}}
