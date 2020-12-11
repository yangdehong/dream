package com.ydh.redsheep.self_mybatis.sqlSession;


import com.ydh.redsheep.self_mybatis.pojo.Configuration;
import com.ydh.redsheep.self_mybatis.pojo.MappedStatement;

import java.util.List;

public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
