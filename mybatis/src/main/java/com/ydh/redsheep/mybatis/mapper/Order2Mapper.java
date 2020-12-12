package com.ydh.redsheep.mybatis.mapper;

import com.ydh.redsheep.mybatis.pojo.Order;
import com.ydh.redsheep.mybatis.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Order2Mapper {

    //查询订单的同时还查询该订单所属的用户

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "total",column = "total"),
            @Result(property = "user",column = "uid",javaType = User.class,
                    one=@One(select = "com.ydh.redsheep.mybatis.mapper.User2Mapper.findUserById"))
    })
    @Select("select * from orders")
    List<Order> findOrderAndUser();


    @Select("select * from orders where uid = #{uid}")
    List<Order> findOrderByUid(Integer uid);




}
