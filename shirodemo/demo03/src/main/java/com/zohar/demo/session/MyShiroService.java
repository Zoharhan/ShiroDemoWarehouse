package com.zohar.demo.session;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.zohar.demo.enums.MyShiroStatus;
import com.zohar.demo.mapper.SysUserOnlineMapper;
import com.zohar.demo.pojo.SysUserOnline;
import com.zohar.demo.service.SysUserOnlineService;
import com.zohar.demo.utils.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MyShiroService {

    @Autowired
    private SysUserOnlineService onlineService;
    @Autowired
    private SysUserOnlineMapper userOnlineMapper;

    /**
     * 根据SessionId获取Session
     *
     * @param sessionId
     * @return
     */
    public Session getSession(Serializable sessionId) {
        SysUserOnline userOnline = onlineService.selectOnlineById(String.valueOf(sessionId));
        return StringUtils.isNull(userOnline) ? null : createSession(userOnline);
    }

    public Session createSession(SysUserOnline userOnline) {
        OnlineSession mySession = new OnlineSession();
        if (StringUtils.isNotNull(userOnline)) {
            mySession.setId(userOnline.getSessionId());
            mySession.setHost(userOnline.getIpaddr());
            mySession.setBrowser(userOnline.getBrowser());
            mySession.setOs(userOnline.getOs());
            mySession.setDeptName(userOnline.getDeptName());
            mySession.setLoginName(userOnline.getLoginName());
            mySession.setStartTimestamp(userOnline.getStartTimestamp());
            mySession.setLastAccessTime(userOnline.getLastAccessTime());
        }
        return mySession;
    }

    /**
     * 删除会话
     *
     * @param session 会话信息
     */
    public void deleteSession(Session session) {
        System.out.println("MyShiroService.deleteSession");
        System.out.println(session.getId());
        onlineService.deleteOnlineById((String) session.getId());
    }

    public void updateMySession(Session session) {
        SysUserOnline userOnline = onlineService.selectOnlineById(String.valueOf(session.getId()));
        System.out.println("String.valueOf(session.getId())\t" + String.valueOf(session.getId()));
        String sessionId = String.valueOf(session.getId());
        OnlineSession onlineSession = (OnlineSession) session;
        System.out.println("StringUtils.isNotNull(onlineSession.getAttribute(\"loginname\"))" + StringUtils.isNotNull(onlineSession.getAttribute("loginname")));
        String loginname = (String) onlineSession.getAttribute("loginname");
        System.out.println("loginname" + loginname);
        if (StringUtils.isNotNull(loginname)) {
            if (StringUtils.isNotNull(userOnline)) {
                userOnline.setLoginName(loginname);
                userOnline.setStatus((MyShiroStatus) onlineSession.getAttribute("userstatus"));
                userOnline.setBrowser(onlineSession.getBrowser());
                userOnline.setOs(onlineSession.getOs());
                userOnline.setLastAccessTime(onlineSession.getLastAccessTime());
                userOnline.setIpaddr(session.getHost());
                userOnline.setSessionId(sessionId);
                userOnline.setIpaddr(onlineSession.getHost());
                userOnlineMapper.updateMySession(userOnline);
            } else {
                SysUserOnline userOnline1 = new SysUserOnline();
                System.out.println("sessionId\t" + sessionId);
                userOnline1.setSessionId(sessionId);
                userOnline1.setLoginName(loginname);
                userOnline1.setStatus((MyShiroStatus) onlineSession.getAttribute("userstatus"));
                userOnline1.setBrowser(onlineSession.getBrowser());
                userOnline1.setOs(onlineSession.getOs());
                userOnline1.setLastAccessTime(onlineSession.getLastAccessTime());
                userOnline1.setIpaddr(onlineSession.getHost());
                userOnline1.setDeptName("1");
                userOnline1.setLoginLocation("1");
                userOnline1.setStartTimestamp(session.getStartTimestamp());
                userOnline1.setLastAccessTime(session.getLastAccessTime());
                userOnlineMapper.createSessionEndurance(userOnline1);
            }
        }
    }

//    public void updateMySession(Session session) {
//        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
//            return; //如果会话过期/停止 没必要再更新了
//        }
//
//        userOnlineMapper.updateMySession(session);
//
//    }
}
