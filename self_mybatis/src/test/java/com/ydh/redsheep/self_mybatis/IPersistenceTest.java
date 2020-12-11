package com.ydh.redsheep.self_mybatis;

import com.ydh.redsheep.self_mybatis.dao.UserMapper;
import com.ydh.redsheep.self_mybatis.pojo.User;
import com.ydh.redsheep.self_mybatis.sqlSession.SqlSession;
import com.ydh.redsheep.self_mybatis.sqlSession.SqlSessionFactory;
import com.ydh.redsheep.self_mybatis.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;

@SpringBootTest
public class IPersistenceTest {

    @Test
    public void test() throws Exception {

        InputStream resourceAsSteam = Thread.currentThread().getContextClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        User userPO = new User();
        userPO.setId(1L);
        userPO.setUsername("张三");
      /*  User user2 = sqlSession.selectOne("user.selectOne", user);

        System.out.println(user2);*/

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }


    }



}
