package com.zohar.demo.service;

import com.zohar.demo.pojo.SysUserOnline;
import org.apache.shiro.session.Session;

public interface SysUserOnlineService {
    /**
     * 获取会话信息
     *
     * @param sessionId
     * @return
     */
    SysUserOnline selectOnlineById(String sessionId);

    /**
     * 删除会话
     *
     * @param sessionId
     */
    void deleteOnlineById(String sessionId);

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    void saveOnline(SysUserOnline online);
}
