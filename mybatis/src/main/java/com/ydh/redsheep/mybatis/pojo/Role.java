package com.ydh.redsheep.mybatis.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Role {

    private Integer id;
    private String roleName;
    private String roleDesc;

}
