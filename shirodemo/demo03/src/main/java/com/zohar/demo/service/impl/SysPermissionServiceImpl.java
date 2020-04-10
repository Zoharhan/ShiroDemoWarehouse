package com.zohar.demo.service.impl;

import com.zohar.demo.mapper.SysPermissionMapper;
import com.zohar.demo.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public Set<String> getUserPermissionByUserId(Integer id) {
        return permissionMapper.getUserPermissionByUserId(id);
    }
}
