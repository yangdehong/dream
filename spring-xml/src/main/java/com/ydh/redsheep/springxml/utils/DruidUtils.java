package com.ydh.redsheep.springxml.utils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author yangdehong
 */
public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://rm-bp14m38byen9x1gs3mo.mysql.rds.aliyuncs.com:3306/ydh_test?useUnicode=true");
        druidDataSource.setUsername("ai_call_engine");
        druidDataSource.setPassword("#Daily_ai_call_engine");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
