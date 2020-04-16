package com.zohar.demo.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

/**
 * 如果使用集群，则继承AbstractSessionDAO
 *
 * @author Zohar
 */
public class MySessionDao extends EnterpriseCacheSessionDAO {


    private int dbSyncPeriod = 1;
    @Autowired
    private MyShiroService myShiroService;
    /**
     * 上次同步数据库的时间戳
     */
    private static final String LAST_SYNC_DB_TIMESTAMP = MySessionDao.class.getName() + "LAST_SYNC_DB_TIMESTAMP";

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("MySessionDao.doCreate");
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        System.out.println(sessionId + "\t" + (OnlineSession) session);
        return session.getId();
    }

    public MySessionDao() {
        super();
    }

    /**
     * 根据会话ID获取会话
     *
     * @param sessionId 会话ID
     * @return ShiroSession
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("MySessionDao.doReadSession\t" + sessionId);

        return myShiroService.getSession(sessionId);
//        return super.doReadSession(sessionId);
    }

    @Override
    protected void doUpdate(Session session) throws UnknownSessionException {
//        System.out.println("MySessionDao.doUpdate");
//        System.out.println(session.getHost());
//        System.out.println(session.getId());
//
        myShiroService.updateMySession(session);
        System.out.println("userstatus:" + session.getAttribute("userstatus"));
        super.doUpdate(session);

    }

    /**
     * 当会话过期/停止（如用户退出时）属性等会调用
     */
    @Override
    protected void doDelete(Session session) {
        System.out.println("MySessionDao.doDelete");
//        MySession mySession = (MySession) session;
//        if (null == mySession)
//        {
//            return;
//        }
//        mySession.setStatus(MyShiroStatus.off_line);
//        myShiroService.deleteSession(mySession);
        myShiroService.deleteSession(session);
//        super.doDelete(session);

    }
}

