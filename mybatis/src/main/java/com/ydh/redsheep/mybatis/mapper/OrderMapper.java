package com.ydh.redsheep.mybatis.mapper;

import com.ydh.redsheep.mybatis.pojo.Order;

import java.util.List;

public interface OrderMapper {

    List<Order> findAll();

    List<Order> findOrderByUid(Integer uid);

}
