package com.ydh.redsheep.self_springmvc.service.impl;

import com.ydh.redsheep.self_springmvc.framework.annotations.MyService;
import com.ydh.redsheep.self_springmvc.service.DemoService;

@MyService("demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public String get(String name) {
        System.out.println("service 实现类中的name参数：" + name) ;
        return name;
    }

}
