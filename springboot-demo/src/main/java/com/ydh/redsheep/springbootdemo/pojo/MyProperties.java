package com.ydh.redsheep.springbootdemo.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:testa.properties")  //配置自定义配置文件的名称及位置
@ConfigurationProperties(prefix = "test")
@Data
@ToString
public class MyProperties {


    private int id;
    private String name;

}
