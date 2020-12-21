package com.ydh.redsheep.self_springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan("com.ydh.redsheep.self_springboot.mapper")
//@MapperScan("com.ydh.redsheep.self_springboot.mapper")
@SpringBootApplication
public class SelfSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelfSpringbootApplication.class, args);
    }

}
