package com.ydh.redsheep.self_mybatis.sqlSession;

import com.ydh.redsheep.self_mybatis.config.BoundSql;
import com.ydh.redsheep.self_mybatis.pojo.Configuration;
import com.ydh.redsheep.self_mybatis.pojo.MappedStatement;
import com.ydh.redsheep.self_mybatis.utils.GenericTokenParser;
import com.ydh.redsheep.self_mybatis.utils.ParameterMapping;
import com.ydh.redsheep.self_mybatis.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {

    private final ParamsHandler paramsHandler = new SimpleParamsHandler();
    private final ResultHandler resultHandler = new SimpleResultHandler();

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        // 2. 获取sql语句 : select * from user where id = #{id} and username = #{username}
        //转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        return doQuery(connection, boundSql, mappedStatement, params);
    }

    private <E> List<E> doQuery(Connection connection, BoundSql boundSql, MappedStatement mappedStatement, Object... params) throws Exception {
        ParamsHandler paramsHandler = new SimpleParamsHandler();
        PreparedStatement preparedStatement = paramsHandler.getPreparedStatement(connection, boundSql, mappedStatement, params);

        // 5. 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        List<E> resultList = resultHandler.getResultList(resultSet, resultType);

        return resultList;
    }

    @Override
    public void insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        doInsert(connection, boundSql, mappedStatement, params);
    }

    private void doInsert(Connection connection, BoundSql boundSql, MappedStatement mappedStatement, Object... params) throws Exception {
        ParamsHandler paramsHandler = new SimpleParamsHandler();
        PreparedStatement preparedStatement = paramsHandler.getPreparedStatement(connection, boundSql, mappedStatement, params);
        // 5. 执行sql
        preparedStatement.execute();
    }

    @Override
    public void update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        doUpdate(connection, boundSql, mappedStatement, params);
    }

    private void doUpdate(Connection connection, BoundSql boundSql, MappedStatement mappedStatement, Object... params) throws Exception {
        ParamsHandler paramsHandler = new SimpleParamsHandler();
        PreparedStatement preparedStatement = paramsHandler.getPreparedStatement(connection, boundSql, mappedStatement, params);
        preparedStatement.execute();
    }

    @Override
    public void delete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        // 1. 注册驱动，获取连接
        Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        doDelete(connection, boundSql, mappedStatement, params);
    }

    private void doDelete(Connection connection, BoundSql boundSql, MappedStatement mappedStatement, Object... params) throws Exception {
        ParamsHandler paramsHandler = new SimpleParamsHandler();
        PreparedStatement preparedStatement = paramsHandler.getPreparedStatement(connection, boundSql, mappedStatement, params);
        preparedStatement.execute();
    }


    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;

    }


    /**
     * 完成对#{}的解析工作：1.将#{}使用？进行代替，2.解析出#{}里面的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql
        String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;

    }


}
