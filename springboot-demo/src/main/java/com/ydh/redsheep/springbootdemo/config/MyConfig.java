package com.ydh.redsheep.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //标明该类为配置类
public class MyConfig {

    @Bean(name = "iservice")   //将返回值对象作为组件添加到spring容器中，标识id默认是方法名
    public MyService myService() {

        return new MyService();
    }


}
