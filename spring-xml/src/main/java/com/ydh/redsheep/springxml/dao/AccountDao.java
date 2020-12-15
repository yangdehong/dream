package com.ydh.redsheep.springxml.dao;


import com.ydh.redsheep.springxml.pojo.Account;

/**
 * @author yangdehong
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
