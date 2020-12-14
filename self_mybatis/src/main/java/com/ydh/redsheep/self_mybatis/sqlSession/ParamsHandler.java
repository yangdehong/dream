package com.ydh.redsheep.self_mybatis.sqlSession;

import com.ydh.redsheep.self_mybatis.config.BoundSql;
import com.ydh.redsheep.self_mybatis.pojo.MappedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface ParamsHandler {

    PreparedStatement getPreparedStatement(Connection connection, BoundSql boundSql, MappedStatement mappedStatement, Object... params) throws Exception;

}
