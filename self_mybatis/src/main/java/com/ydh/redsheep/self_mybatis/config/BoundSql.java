package com.ydh.redsheep.self_mybatis.config;

import com.ydh.redsheep.self_mybatis.utils.ParameterMapping;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BoundSql {

    private String sqlText; //解析过后的sql

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

}
