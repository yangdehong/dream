package com.ydh.redsheep.springssm.service.impl;

import com.ydh.redsheep.springssm.mapper.AccountMapper;
import com.ydh.redsheep.springssm.pojo.Account;
import com.ydh.redsheep.springssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> queryAccountList() throws Exception {
        return accountMapper.queryAccountList();
    }
}
