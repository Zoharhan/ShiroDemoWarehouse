package com.zohar.demo.service.impl;

import com.zohar.demo.mapper.SysUserOnlineMapper;
import com.zohar.demo.pojo.SysUserOnline;
import com.zohar.demo.service.SysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserOnlineServiceImpl implements SysUserOnlineService {

    @Autowired
    private SysUserOnlineMapper onlineMapper;

    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return onlineMapper.selectOnlineById(sessionId);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public void deleteOnlineById(String sessionId) {
        System.out.println("SysUserOnlineServiceImpl.deleteOnlineById");
        onlineMapper.deleteOnlineById(sessionId);
    }

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    @Override
    public void saveOnline(SysUserOnline online) {

    }
}
