package com.ydh.redsheep.springtx.dao;


import com.ydh.redsheep.springtx.pojo.Account;

/**
 * @author yangdehong
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
