package com.zohar.demo.service;

import java.util.Set;

public interface SysPermissionService {
    Set<String> getUserPermissionByUserId(Integer id);
}
