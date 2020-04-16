package com.zohar.demo.session;

import com.zohar.demo.utils.IpUtils;
import com.zohar.demo.utils.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;


public class MyShiroSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext sessionContext) {
        System.out.println("MyShiroSessionFactory.createSession");
        OnlineSession session = new OnlineSession();
        if (sessionContext != null && sessionContext instanceof WebSessionContext) {
            WebSessionContext websessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) websessionContext.getServletRequest();
            if (request != null) {
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                session.setHost(IpUtils.getIpAddr(request));
                session.setBrowser(browser);
                session.setOs(os);
                session.setLoginName("guest");
            }
        }
        return session;
    }
}
