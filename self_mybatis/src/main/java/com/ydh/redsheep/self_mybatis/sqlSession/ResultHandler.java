package com.ydh.redsheep.self_mybatis.sqlSession;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.List;

public interface ResultHandler {

    <E> List<E> getResultList(ResultSet resultSet, String resultType) throws Exception;

}
