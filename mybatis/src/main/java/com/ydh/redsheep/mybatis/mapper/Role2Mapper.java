package com.ydh.redsheep.mybatis.mapper;

import com.ydh.redsheep.mybatis.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Role2Mapper {


    @Select("select * from sys_role r,sys_user_role ur where r.id = ur.roleid and ur.userid = #{uid}")
    List<Role> findRoleByUid(Integer uid);
}
