package com.ydh.redsheep.springanno.dao;


import com.ydh.redsheep.springanno.pojo.Account;

/**
 * @author yangdehong
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
