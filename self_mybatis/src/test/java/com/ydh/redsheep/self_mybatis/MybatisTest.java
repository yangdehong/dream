package com.ydh.redsheep.self_mybatis;

import com.ydh.redsheep.self_mybatis.dao.UserMapper;
import com.ydh.redsheep.self_mybatis.pojo.User;
import com.ydh.redsheep.self_mybatis.sqlSession.SqlSession;
import com.ydh.redsheep.self_mybatis.sqlSession.SqlSessionFactory;
import com.ydh.redsheep.self_mybatis.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
public class MybatisTest {

    private UserMapper userMapper;

    @Before
    public void before() throws Exception{
        InputStream resourceAsSteam = Thread.currentThread().getContextClassLoader().getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void test() throws Exception {
        System.out.println("===========查询所有数据");
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println("查询所有数据"+user);
        }
        System.out.println("===========条件查询");
        User user = new User();
        user.setId(1);
        User user1 = userMapper.findByCondition(user);
        System.out.println("条件查询"+user1);
        System.out.println("===========新增数据");
        User user2 = new User();
        user2.setUsername("名字");
        userMapper.addUser(user2);
        System.out.println("===========修改数据");
        User user3 = new User();
        user3.setId(1);
        user3.setUsername("名字3333");
        userMapper.updateUser(user3);
        System.out.println("===========删除数据");
        User user4 = new User();
        user4.setId(10);
        userMapper.deleteUser(user4);
    }

}
