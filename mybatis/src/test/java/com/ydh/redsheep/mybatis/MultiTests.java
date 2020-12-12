package com.ydh.redsheep.mybatis;

import com.ydh.redsheep.mybatis.mapper.*;
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
class MultiTests {

    private UserMapper userMapper;
    private OrderMapper orderMapper;
    private User2Mapper user2Mapper;
    private Order2Mapper order2Mapper;
    private Role2Mapper role2Mapper;

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
        user2Mapper = sqlSession.getMapper(User2Mapper.class);
        order2Mapper = sqlSession.getMapper(Order2Mapper.class);
        role2Mapper = sqlSession.getMapper(Role2Mapper.class);
    }

    @Test
    public void test() {
        System.out.println("======一对一查询");
        List<Order> orderList = orderMapper.findAll();
        for (Order order : orderList) {
            System.out.println(order);
        }
        System.out.println("======一对多查询");
        List<User> userList = userMapper.findAllUserAndOrder();
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println("======多对多查询");
        List<User> userList2 = userMapper.findAllUserAndRole();
        for (User user : userList2) {
            System.out.println(user);
        }
    }

    /**
     * 基于注释的
     */
    @Test
    public void test2() {
        System.out.println("======新增数据");
        User addUser = new User();
        addUser.setUsername("测试数据");
        user2Mapper.addUser(addUser);
        System.out.println("======查询数据");
        List<User> userList = user2Mapper.selectUser();
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println("======修改数据");
        User updateUser = new User();
        updateUser.setId(6);
        updateUser.setUsername("修改了测试数据");
        user2Mapper.updateUser(updateUser);
        System.out.println("======删除数据");
        user2Mapper.deleteUser(6);
    }

    /**
     * 基于注释的
     */
    @Test
    public void test3() {
        System.out.println("======一对一查询");
        List<Order> orderAndUser = order2Mapper.findOrderAndUser();
        for (Order order : orderAndUser) {
            System.out.println(order);
        }
        System.out.println("======一对多查询");
        List<User> all = user2Mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        System.out.println("======多对多查询");
        List<User> all2 = user2Mapper.findAllUserAndRole();
        for (User user : all2) {
            System.out.println(user);
        }
    }

}
