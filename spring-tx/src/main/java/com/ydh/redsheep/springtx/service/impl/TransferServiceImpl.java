package com.ydh.redsheep.springtx.service.impl;

import com.ydh.redsheep.springtx.dao.AccountDao;
import com.ydh.redsheep.springtx.pojo.Account;
import com.ydh.redsheep.springtx.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author yangdehong
 */
@Service("transferService")
@Transactional
public class TransferServiceImpl implements TransferService {

    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

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
