package com.zohar.demo.service.impl;

import com.zohar.demo.mapper.SysRoleMapper;
import com.zohar.demo.pojo.SysRole;
import com.zohar.demo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SysRoleSerivceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public Set<String> getUserRoleByUserId(Integer id) {
        return roleMapper.getUserRoleByUserId(id);
    }
}
