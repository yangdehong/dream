package com.ydh.redsheep.mybatis;

import com.ydh.redsheep.mybatis.mapper.UserMapper;
import com.ydh.redsheep.mybatis.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@SpringBootTest
class QuickStartTests {

    private SqlSession sqlSession;

    @PostConstruct
    public void before() throws Exception {
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        sqlSession = sqlSessionFactory.openSession(true);// 默认开启一个事务，但是该事务不会自动提交，true是默认提交事务
    }

    @Test
    public void test() {
        //在进行增删改操作时，要手动提交事务
        // 4.sqlSession调用方法：查询所有selectList  查询单个：selectOne 添加：insert  修改：update 删除：delete
        System.out.println("=========获取所有数据=========");
        List<User> users = sqlSession.selectList("com.ydh.redsheep.mybatis.mapper.UserMapper.findAll");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("=========新增数据=========");
        User insertUser = new User();
        insertUser.setUsername("tom");
        sqlSession.insert("com.ydh.redsheep.mybatis.mapper.UserMapper.saveUser",insertUser);
        System.out.println("=========修改数据=========");
        User updateUser = new User();
        updateUser.setId(1);
        updateUser.setUsername("lucy999");
        sqlSession.update("com.ydh.redsheep.mybatis.mapper.UserMapper.updateUser",updateUser);
        System.out.println("=========删除数据=========");
        sqlSession.delete("com.ydh.redsheep.mybatis.mapper.UserMapper.deleteUser",2);


        sqlSession.close();
    }

    @Test
    public void test2()  {

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println("11111="+user);
        }

        User user0 = new User();
        user0.setId(1);
        user0.setUsername("lucy999");
        List<User> all2 = userMapper.findByCondition(user0);
        for (User user : all2) {
            System.out.println("22222="+user);
        }

        int[] arr = {1, 2, 3};
        List<User> all3 = userMapper.findByIds(arr);
        for (User user : all3) {
            System.out.println("33333="+user);
        }

    }

}
