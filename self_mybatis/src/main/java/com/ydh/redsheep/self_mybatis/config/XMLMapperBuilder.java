package com.ydh.redsheep.self_mybatis.config;

import com.ydh.redsheep.self_mybatis.pojo.Configuration;
import com.ydh.redsheep.self_mybatis.pojo.MappedStatement;
import com.ydh.redsheep.self_mybatis.pojo.myenum.SqlTypeEnum;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();
        String namespace = rootElement.attributeValue("namespace");
        List<Element> elements = rootElement.elements();
        elements.forEach(element -> {
            SqlTypeEnum sqlTypeEnum = SqlTypeEnum.UNKNOWN;
            String name = element.getName();
            switch (name) {
                case "select":
                    sqlTypeEnum = SqlTypeEnum.SELECT;
                    break;
                case "update":
                    sqlTypeEnum = SqlTypeEnum.UPDATE;
                    break;
                case "insert":
                    sqlTypeEnum = SqlTypeEnum.INSERT;
                    break;
                case "delete":
                    sqlTypeEnum = SqlTypeEnum.DELETE;
                    break;
            }
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setSqlTypeEnum(sqlTypeEnum);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        });

    }


}
