package com.ydh.redsheep.springbootdemo;

import com.ydh.redsheep.springbootdemo.controller.HelloController;
import com.ydh.redsheep.springbootdemo.pojo.MyProperties;
import com.ydh.redsheep.springbootdemo.pojo.Person;
import com.ydh.redsheep.springbootdemo.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringbootDemoApplicationTests {

    //入门案例测试
    @Autowired
    private HelloController helloController;

    @Test
    void contextLoad1() {
        String demo = helloController.demo();
        System.out.println(demo);
    }


    /*
      配置文件properties配置测试
     */

    @Autowired
    private Person person;

    @Test
    void configurationTest() {
        System.out.println(person);
    }


    /*
      @Value进行测试
     */

    @Autowired
    private Student student;

    @Test
    void studentTest() {
        System.out.println(student);
    }



    /*
      @propertySource进行测试
     */

    @Autowired
    private MyProperties myProperties;

    @Test
    void myPropertiesTest() {
        System.out.println(myProperties);
    }


     /*
      @Configuration进行测试
     */

    @Autowired
    private ApplicationContext context;

    @Test
    void iocTest() {
        System.out.println(context.containsBean("iservice"));
    }


    /*
      测试随机数及参数间引用
     */
    @Value("${tom.description}")
    private String description;


    @Test
    void placeholderTest() {
        System.out.println(description);
    }


    /*
     测试随机数及参数间引用
    */
//    @Autowired
//    private SimpleBean simpleBean;
//
//
//    @Test
//    void zdyStarterTest(){
//        System.out.println(simpleBean);
//    }



}
