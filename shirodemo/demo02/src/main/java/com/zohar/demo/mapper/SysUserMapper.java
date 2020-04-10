package com.zohar.demo.mapper;

import com.zohar.demo.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserMapper {


    @Select("SELECT id,nickname,email,pswd,`status` FROM `u_user` where email = #{username} and pswd = #{password}")
    SysUser login(String username, String password);
}
