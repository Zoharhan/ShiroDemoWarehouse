package com.zohar.demo.service.impl;

import com.zohar.demo.mapper.SysUserMapper;
import com.zohar.demo.pojo.SysUser;
import com.zohar.demo.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public SysUser login(String username, String password) {
        return userMapper.login(username,password);
    }
}
