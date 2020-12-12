package com.ydh.redsheep.mybatis;

import com.ydh.redsheep.mybatis.mapper.OrderMapper;
import com.ydh.redsheep.mybatis.mapper.UserMapper;
import com.ydh.redsheep.mybatis.pojo.Order;
import com.ydh.redsheep.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class CacheTests {

    private UserMapper userMapper;
    private OrderMapper orderMapper;

    @PostConstruct
    public void before() throws Exception {
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);// 默认开启一个事务，但是该事务不会自动提交，true是默认提交事务
        userMapper = sqlSession.getMapper(UserMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }

    @Test
    public void test() {

    }


}
