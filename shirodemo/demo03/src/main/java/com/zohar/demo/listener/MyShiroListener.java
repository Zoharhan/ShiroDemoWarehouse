package com.zohar.demo.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class MyShiroListener implements SessionListener {
    /**
     * 会话的生命周期开始
     *
     * @param session
     */
    @Override
    public void onStart(Session session) {
        System.out.println("MyShiroListener.onStart");
    }

    /**
     * 会话生命周期结束
     *
     * @param session
     */
    @Override
    public void onStop(Session session) {
        System.out.println("MyShiroListener.onStop");
    }

    /**
     * 会话过期
     *
     * @param session
     */
    @Override
    public void onExpiration(Session session) {
        System.out.println("MyShiroListener.onExpiration");
    }


}
