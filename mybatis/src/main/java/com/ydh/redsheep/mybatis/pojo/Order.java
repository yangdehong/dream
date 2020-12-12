package com.ydh.redsheep.mybatis.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {

    private Integer id;
    private String orderTime;
    private Double total;

    // 表明该订单属于哪个用户
    private User user;
}
