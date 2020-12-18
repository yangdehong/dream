package com.ydh.redsheep.springssm.service;


import com.ydh.redsheep.springssm.pojo.Account;

import java.util.List;

public interface AccountService {
    List<Account> queryAccountList() throws Exception;
}
