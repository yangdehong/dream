package com.ydh.redsheep.self_mybatis.sqlSession;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleResultHandler implements ResultHandler {

    @Override
    public <E> List<E> getResultList(ResultSet resultSet, String resultType) throws Exception {
        Class<?> resultTypeClass = getClassType(resultType);
        ArrayList<Object> objects = new ArrayList<>();
        // 6. 封装返回结果集
        while (resultSet.next()) {
            Object obj = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                // 字段名
                String columnName = metaData.getColumnName(i);
                // 字段的值
                Object value = resultSet.getObject(columnName);
                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                // 转换类型
                value = transformType(writeMethod, value);
                writeMethod.invoke(obj, value);

            }
            objects.add(obj);

        }
        return (List<E>) objects;
    }

    public Object transformType(Method writeMethod, Object value) throws Exception {
        Class<?>[] parameterTypes = writeMethod.getParameterTypes();
        // 只去判断单个参数的
        if (parameterTypes.length == 1) {
            Class<?> parameterType = parameterTypes[0];
            String canonicalName = parameterType.getCanonicalName();
            if ("java.util.Date".equals(canonicalName) && value != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
                Date date = simpleDateFormat.parse(value.toString());
                return date;
            }
        } else {
            // TDOO 不浪费时间写多个的
        }
        return value;
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;

    }
}
