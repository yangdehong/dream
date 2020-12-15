package com.ydh.redsheep.self_spring.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author yangdehong
 */
@Data
@ToString
public class Account {

    private String cardNo;
    private String name;
    private int money;

}
