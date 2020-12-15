package com.ydh.redsheep.springxmlanno;

import com.ydh.redsheep.springxmlanno.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LazyTest {

    @Test
    void test() throws Exception {
        // xml方式推荐的非web方式启动
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao);

//        applicationContext.close();

    }

}
