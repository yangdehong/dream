package com.ydh.redsheep.self_mybatis.sqlSession;

import com.ydh.redsheep.self_mybatis.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
