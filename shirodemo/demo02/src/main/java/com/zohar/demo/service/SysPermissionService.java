package com.zohar.demo.service;

import com.zohar.demo.pojo.SysPermission;

import java.util.Set;

public interface SysPermissionService {
    Set<String> getUserPermissionByUserId(Integer id);
}
