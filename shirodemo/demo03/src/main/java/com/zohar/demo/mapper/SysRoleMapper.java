package com.zohar.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Mapper
@Repository
public interface SysRoleMapper {

    @Select("SELECT `name` FROM `u_role` ur JOIN u_user_role uur on  uur.rid = ur.id WHERE uur.uid = #{id} ")
    Set<String> getUserRoleByUserId(Integer id);
}
