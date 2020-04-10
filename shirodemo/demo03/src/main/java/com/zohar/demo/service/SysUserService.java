package com.zohar.demo.service;

import com.zohar.demo.pojo.SysUser;

public interface SysUserService {
    SysUser login(String username, String password);
}
