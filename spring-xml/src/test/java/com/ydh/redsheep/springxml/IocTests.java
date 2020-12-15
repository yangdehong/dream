package com.ydh.redsheep.springxml;

import com.ydh.redsheep.springxml.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTests {

    @Test
    void test() throws Exception {
        // xml方式推荐的非web方式启动
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 第一次getBean该对象
        Object accountPojo = applicationContext.getBean("accountPojo");

        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");

        accountDao.queryAccountByCardNo("1111111");
        System.out.println("accountDao：" + accountDao);
        AccountDao accountDao1 = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println("accountDao1：" + accountDao1);


        Object connectionUtils = applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);

//        applicationContext.close();

    }

}
