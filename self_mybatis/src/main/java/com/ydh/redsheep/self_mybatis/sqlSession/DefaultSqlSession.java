package com.ydh.redsheep.self_mybatis.sqlSession;


import com.ydh.redsheep.self_mybatis.pojo.Configuration;
import com.ydh.redsheep.self_mybatis.pojo.MappedStatement;
import com.ydh.redsheep.self_mybatis.pojo.User;
import com.ydh.redsheep.self_mybatis.pojo.myenum.SqlTypeEnum;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Map<String, MappedStatement> mappedStatementMap;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.mappedStatementMap = configuration.getMappedStatementMap();
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        MappedStatement mappedStatement = mappedStatementMap.get(statementid);
        //将要去完成对simpleExecutor里的query方法的调用
        Executor executor = new SimpleExecutor();
        List<Object> list = executor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if(objects.size()==1){
            return (T) objects.get(0);
        }else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public void insert(String statementid, Object... params) throws Exception {
        MappedStatement mappedStatement = mappedStatementMap.get(statementid);
        Executor executor = new SimpleExecutor();
        executor.insert(configuration, mappedStatement, params);
    }

    @Override
    public void update(String statementid, Object... params) throws Exception {
        MappedStatement mappedStatement = mappedStatementMap.get(statementid);
        Executor executor = new SimpleExecutor();
        executor.update(configuration, mappedStatement, params);
    }

    @Override
    public void delete(String statementid, Object... params) throws Exception {
        MappedStatement mappedStatement = mappedStatementMap.get(statementid);
        Executor executor = new SimpleExecutor();
        executor.delete(configuration, mappedStatement, params);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 //根据不同情况，来调用selectList或者selectOne
                // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名：findAll
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = className+"."+methodName;
                // 获取MappedStatement中的sqlTypeEnum
                Map<String, MappedStatement> mappedStatementMap = configuration.getMappedStatementMap();
                MappedStatement mappedStatement = mappedStatementMap.get(statementId);
                if (mappedStatement == null) {
                    return null;
                }
                SqlTypeEnum sqlTypeEnum = mappedStatement.getSqlTypeEnum();
                switch (sqlTypeEnum) {
                    case SELECT:
                        // 准备参数2：params:args
                        // 获取被调用方法的返回值类型
                        Type genericReturnType = method.getGenericReturnType();
                        // 判断是否进行了 泛型类型参数化
                        if(genericReturnType instanceof ParameterizedType){
                            List<Object> objects = selectList(statementId, args);
                            return objects;
                        }
                        return selectOne(statementId,args);
                    case UPDATE:
                        update(statementId, args);
                        break;
                    case INSERT:
                        insert(statementId, args);
                        break;
                    case DELETE:
                        delete(statementId, args);
                        break;
                }
                return null;
            }
        });

        return (T) proxyInstance;
    }




}
