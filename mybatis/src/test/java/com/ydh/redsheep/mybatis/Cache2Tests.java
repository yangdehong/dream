package com.ydh.redsheep.mybatis;

import com.ydh.redsheep.mybatis.mapper.User2Mapper;
import com.ydh.redsheep.mybatis.mapper.UserMapper;
import com.ydh.redsheep.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * xml配置的二级缓存源码入口
 */
@SpringBootTest
class Cache2Tests {

    @Test
    public void test() throws Exception{
        //1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.解析了配置文件，并创建了sqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        System.out.println("第一次查询");
        User user1 = sqlSession1.selectOne("com.ydh.redsheep.mybatis.mapper.UserMapper.findUserById",1);
        sqlSession1.close(); //清空一级缓存，不让一级缓存造成影响

        // 清空之后就再次查询缓存就没有了
        User user = new User();
        user.setId(1);
        user.setUsername("lisi");
        sqlSession3.update("com.ydh.redsheep.mybatis.mapper.UserMapper.updateUser", user);
        sqlSession3.commit();

        System.out.println("第二次查询");
        User user2 = sqlSession2.selectOne("com.ydh.redsheep.mybatis.mapper.UserMapper.findUserById",1);

        System.out.println(user1==user2); // false


    }


}
