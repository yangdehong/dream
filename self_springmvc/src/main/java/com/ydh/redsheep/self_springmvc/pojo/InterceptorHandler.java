package com.ydh.redsheep.self_springmvc.pojo;

import java.lang.reflect.Method;

/**
* 拦截器接受的
* @author : yangdehong
* @date : 2020/12/19 16:30
*/
public class InterceptorHandler {

    private Object object;

    private Method method;

    public InterceptorHandler(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
