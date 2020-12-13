package com.ydh.redsheep.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ydh.redsheep.mybatis.mapper.User2Mapper;
import com.ydh.redsheep.mybatis.mapper.User3Mapper;
import com.ydh.redsheep.mybatis.pojo.User;
import com.ydh.redsheep.mybatis.pojo.User3;
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
public class PluginTest {

    private User2Mapper userMapper;
    private User3Mapper user3Mapper;

    @PostConstruct
    public void before() throws Exception {
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);// 默认开启一个事务，但是该事务不会自动提交，true是默认提交事务
        userMapper = sqlSession.getMapper(User2Mapper.class);
        user3Mapper = sqlSession.getMapper(User3Mapper.class);
    }

    @Test
    public void test() {
        PageHelper.startPage(1, 1);
        List<User> userList = userMapper.selectUser();
        for (User user : userList) {
            System.out.println(user);
        }
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("当前页："+pageInfo.getPageNum());
        System.out.println("每页显示的条数："+pageInfo.getPageSize());
    }

    @Test
    public void test2() {
        User3 user3 = user3Mapper.selectByPrimaryKey(1);
        System.out.println(user3);
    }

}
