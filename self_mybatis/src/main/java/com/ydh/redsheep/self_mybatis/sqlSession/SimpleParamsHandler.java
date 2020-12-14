package com.ydh.redsheep.self_mybatis.sqlSession;

import com.ydh.redsheep.self_mybatis.config.BoundSql;
import com.ydh.redsheep.self_mybatis.pojo.MappedStatement;
import com.ydh.redsheep.self_mybatis.utils.ParameterMapping;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SimpleParamsHandler implements ParamsHandler {

    @Override
    public PreparedStatement getPreparedStatement(Connection connection, BoundSql boundSql, MappedStatement mappedStatement, Object... params) throws Exception {
        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        // 4. 设置参数
        //获取到了参数的全路径
        String paramterType = mappedStatement.getParameterType();
        Class<?> paramtertypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            String content = parameterMapping.getContent();
            //反射
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(true);
            Object o = declaredField.get(params[0]);

            preparedStatement.setObject(i + 1, o);

        }
        return preparedStatement;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;

    }

}
