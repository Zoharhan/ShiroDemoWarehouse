package com.zohar.demo.mapper;

import com.zohar.demo.pojo.SysUserOnline;
import com.zohar.demo.session.OnlineSession;
import org.apache.ibatis.annotations.*;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Mapper
@Repository
public interface SysUserOnlineMapper {

    @Insert("INSERT INTO u_user_online(sessionId,login_name,dept_name,ipaddr,login_location,browser,os,status,start_timestamp,last_access_time) VALUES(#{sessionId} ,#{loginName},#{deptName} ,#{ipaddr} ,#{loginLocation} ,#{browser} ,#{os} ,#{status} ,#{startTimestamp} ,#{lastAccessTime}  )")
    void createSessionEndurance(SysUserOnline session);

    @Update("UPDATE u_user_online SET login_name=#{loginName}  ,dept_name = #{deptName} ,ipaddr = #{ipaddr} ,login_location = #{loginLocation} ,browser = #{browser} , os= #{os} ,`status` = #{status} ,last_access_time = #{lastAccessTime}  WHERE sessionId = #{sessionId} ")
    void updateMySession(SysUserOnline session);

    @Select("SELECT sessionId,login_name,dept_name,ipaddr,login_location,browser,os,`status`,start_timestamp,last_access_time FROM `u_user_online` WHERE sessionId = #{sessionId} ")
    SysUserOnline selectOnlineById(String sessionId);

    @Delete("DELETE FROM u_user_online WHERE sessionId = #{sessionId} ")
    void deleteOnlineById(String sessionId);
}
