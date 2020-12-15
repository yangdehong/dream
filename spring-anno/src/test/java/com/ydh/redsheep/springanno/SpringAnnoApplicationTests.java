package com.ydh.redsheep.springanno;


import com.ydh.redsheep.springanno.config.SpringConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnoApplicationTests {

    @Test
    public void test() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        Object accountDao = applicationContext.getBean("accountDao");
        System.out.println(accountDao);
    }

}
