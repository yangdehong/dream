package com.ydh.redsheep.self_mybatis.sqlSession;

import java.util.List;

public interface SqlSession {

    //查询所有
    <E> List<E> selectList(String statementid,Object... params) throws Exception;

    //根据条件查询单个
    <T> T selectOne(String statementid,Object... params) throws Exception;

    //为Dao接口生成代理实现类
    <T> T getMapper(Class<?> mapperClass);

}
