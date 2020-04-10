package com.zohar.demo.service;

import com.zohar.demo.pojo.SysRole;

import java.util.Set;

public interface SysRoleService {
    Set<String> getUserRoleByUserId(Integer id);
}
