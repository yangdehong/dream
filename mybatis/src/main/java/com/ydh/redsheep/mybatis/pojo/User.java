package com.ydh.redsheep.mybatis.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private Date birthday;
    //表示用户关联的订单
    private List<Order> orderList = new ArrayList<>();
    //表示用户关联的角色
    private List<Role> roleList = new ArrayList<>();

}
