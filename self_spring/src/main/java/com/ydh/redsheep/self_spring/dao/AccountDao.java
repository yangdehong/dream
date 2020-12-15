package com.ydh.redsheep.self_spring.dao;

import com.ydh.redsheep.self_spring.pojo.Account;

/**
 * @author yangdehong
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
