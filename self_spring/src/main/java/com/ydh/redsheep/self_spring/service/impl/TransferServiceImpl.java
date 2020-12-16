package com.ydh.redsheep.self_spring.service.impl;

import com.ydh.redsheep.self_spring.anno.MyAutowired;
import com.ydh.redsheep.self_spring.anno.MyService;
import com.ydh.redsheep.self_spring.anno.MyTransactional;
import com.ydh.redsheep.self_spring.dao.AccountDao;
import com.ydh.redsheep.self_spring.pojo.Account;
import com.ydh.redsheep.self_spring.service.TransferService;
import org.springframework.stereotype.Service;

/**
 * @author yangdehong
 */
@MyTransactional
@MyService("transferService")
public class TransferServiceImpl implements TransferService {

    // 最佳状态
    @MyAutowired
    private AccountDao accountDao;

//    // 构造函数传值/set方法传值
//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney()-money);
            to.setMoney(to.getMoney()+money);

            accountDao.updateAccountByCardNo(to);
            int c = 1/0;
            accountDao.updateAccountByCardNo(from);

    }
}
