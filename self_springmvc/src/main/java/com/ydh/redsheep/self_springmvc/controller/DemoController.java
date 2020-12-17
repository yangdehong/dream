package com.ydh.redsheep.self_springmvc.controller;

import com.ydh.redsheep.self_springmvc.framework.annotations.MyAutowired;
import com.ydh.redsheep.self_springmvc.framework.annotations.MyController;
import com.ydh.redsheep.self_springmvc.framework.annotations.MyRequestMapping;
import com.ydh.redsheep.self_springmvc.service.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyAutowired
    private DemoService demoService;

    /**
     * URL: /demo/query?name=lisi
     * @param request
     * @param response
     * @param name
     * @return
     */
    @MyRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response,String name) {
        return demoService.get(name);
    }
}
