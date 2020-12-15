package com.ydh.redsheep.springxml.factory;


import com.ydh.redsheep.springxml.utils.ConnectionUtils;

/**
 * @author 应癫
 */
public class CreateBeanFactory {



    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }


    public ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }
}
