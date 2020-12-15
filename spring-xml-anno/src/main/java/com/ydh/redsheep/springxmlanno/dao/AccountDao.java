package com.ydh.redsheep.springxmlanno.dao;

import com.ydh.redsheep.springxmlanno.pojo.Account;

/**
 * @author yangdehong
 */
public interface AccountDao {

    Account queryAccountByCardNo(String cardNo) throws Exception;

    int updateAccountByCardNo(Account account) throws Exception;
}
