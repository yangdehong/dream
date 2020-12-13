package com.ydh.redsheep.mybatis;

import com.ydh.redsheep.mybatis.mapper.OrderMapper;
import com.ydh.redsheep.mybatis.mapper.User2Mapper;
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

    private User2Mapper userMapper;

    @PostConstruct
    public void before() throws Exception {
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3.生产sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);// 默认开启一个事务，但是该事务不会自动提交，true是默认提交事务
        userMapper = sqlSession.getMapper(User2Mapper.class);
    }

    /**
     * 一级缓存测试
     */
    @Test
    public void testFirstLevelCache() {
        // 1、第一次查询id=1
        User u1 = userMapper.findUserById(1); // 打印sql
        System.out.println(u1);
        // 2、第一次查询id=2
        User u12 = userMapper.findUserById(2); // 打印sql
        System.out.println(u12);
        // 3、更新用户，如果不更新第二次的都会从缓存中获取
        User user = new User();
        user.setId(1);
        user.setUsername("tom");
        userMapper.updateUser(user);
        // 4、第二次查询id=1
        User u2 = userMapper.findUserById(1); // 打印sql
        System.out.println(u2);
        // 5、第二次查询id=2
        User u22 = userMapper.findUserById(2); // 打印sql
        System.out.println(u22);
        System.out.println(u1==u2);
        System.out.println(u12==u22);
    }

    @Test
    public void testSecondLevelCache() throws Exception{
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        User2Mapper mapper1 = sqlSession1.getMapper(User2Mapper.class);
        User2Mapper mapper2 = sqlSession2.getMapper(User2Mapper.class);
        User2Mapper mapper3 = sqlSession3.getMapper(User2Mapper.class);

        System.out.println("第一次查询");
        User user1 = mapper1.findUserById(1);
        sqlSession1.close(); //清空一级缓存，不让一级缓存造成影响

        // 清空之后就再次查询缓存就没有了
//        User user = new User();
//        user.setId(1);
//        user.setUsername("lisi");
//        mapper3.updateUser(user);
//        sqlSession3.commit();

        System.out.println("第二次查询");
        User user2 = mapper2.findUserById(1);

        System.out.println(user1==user2); // false


    }


}
