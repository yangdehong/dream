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
public class PersistenceTest {

    @Test
    public void test() throws Exception {

        InputStream resourceAsSteam = Thread.currentThread().getContextClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }

        System.out.println("===========");

        User user = new User();
        user.setId(1L);
        user.setUsername("测试");
        User user1 = userMapper.findByCondition(user);
        System.out.println(user1);

    }

}
