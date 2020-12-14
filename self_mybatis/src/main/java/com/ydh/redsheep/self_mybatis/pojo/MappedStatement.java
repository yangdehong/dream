package com.ydh.redsheep.self_mybatis.pojo;

import com.ydh.redsheep.self_mybatis.pojo.myenum.SqlTypeEnum;
import lombok.Data;

@Data
public class MappedStatement {

    //id标识
    private String id;
    //返回值类型
    private String resultType;
    //参数值类型
    private String parameterType;
    //sql语句
    private String sql;
    // CRUD的类型
    private SqlTypeEnum sqlTypeEnum;

}
