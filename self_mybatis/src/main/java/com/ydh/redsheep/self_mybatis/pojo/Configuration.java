package com.ydh.redsheep.self_mybatis.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
*
* @author : yangdehong
* @date : 2020/12/11 16:33
*/
@Data
public class Configuration {

    private DataSource dataSource;

    /**
     * key: statementid  value:封装好的mappedStatement对象
     */
    Map<String,MappedStatement> mappedStatementMap = new HashMap<>();

}
