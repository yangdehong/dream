package com.ydh.redsheep.self_spring;

import com.ydh.redsheep.self_spring.anno.MyComponent;
import com.ydh.redsheep.self_spring.factory.BeanFactory;
import com.ydh.redsheep.self_spring.factory.ProxyFactory;
import com.ydh.redsheep.self_spring.service.TransferService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@SpringBootTest
public class ControllerTests {

    // 1. 实例化service层对象
    //private TransferService transferService = new TransferServiceImpl();
    //private TransferService transferService = (TransferService) BeanFactory.getBean("transferService");

    // 从工厂获取委托对象（委托对象是增强了事务控制的功能）

    // 首先从BeanFactory获取到proxyFactory代理工厂的实例化对象
    private ProxyFactory proxyFactory = (ProxyFactory) BeanFactory.getBean("proxyFactory");
    private TransferService transferService = (TransferService) proxyFactory.getJdkProxy(BeanFactory.getBean("transferService")) ;

    @Test
    public void test() throws Exception {
        String fromCardNo = "9999999999";
        String toCardNo = "8888888888";
        int money = 100;
        //  2. 调用service层方法
        transferService.transfer(fromCardNo,toCardNo,money);
        System.out.println("========");
    }

}
