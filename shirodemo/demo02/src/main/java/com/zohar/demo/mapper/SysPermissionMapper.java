package com.zohar.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface SysPermissionMapper {

    @Select("SELECT DISTINCT perms FROM `u_permission` up JOIN u_role_permission urp on up.id = urp.pid JOIN u_user_role uur on uur.rid = urp.rid WHERE uur.uid = #{id} ")
    Set<String> getUserPermissionByUserId(Integer id);
}
