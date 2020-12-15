package com.ydh.redsheep.springxml.factory;


import com.ydh.redsheep.springxml.utils.ConnectionUtils;

/**
 * @author yangdehong
 */
public class CreateBeanFactory {



    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }


    public ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }
}
