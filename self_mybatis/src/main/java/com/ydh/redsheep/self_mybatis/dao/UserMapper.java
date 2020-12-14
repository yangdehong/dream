package com.ydh.redsheep.self_mybatis.dao;


import com.ydh.redsheep.self_mybatis.pojo.User;

import java.util.List;

public interface UserMapper {

    //查询所有用户
    List<User> findAll() throws Exception;

    //根据条件进行用户查询
    User findByCondition(User user) throws Exception;

    //添加用户
    void addUser(User user);

    //更新用户
    void updateUser(User user);

    //删除用户
    void deleteUser(User user);

}
