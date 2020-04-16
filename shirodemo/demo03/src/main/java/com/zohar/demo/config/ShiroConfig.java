package com.zohar.demo.config;

import com.zohar.demo.listener.MyShiroListener;
import com.zohar.demo.realm.UserRealm;
import com.zohar.demo.session.MySessionDao;
import com.zohar.demo.session.MyShiroSessionFactory;
import com.zohar.demo.utils.SpringUtils;
import com.zohar.demo.utils.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.config.ConfigurationException;
import org.apache.shiro.io.ResourceUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
//    private final static Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    // 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }


    //将自己的验证方式加入容器
    @Bean
    public UserRealm userRealm(EhCacheManager cacheManage) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManage);
        return userRealm;
    }

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.getCacheManager("zohar");
        EhCacheManager em = new EhCacheManager();
        if (StringUtils.isNull(cacheManager)) {
            em.setCacheManager(new net.sf.ehcache.CacheManager(getCacheManagerConfigFileInputStream()));
            return em;
        } else {
            em.setCacheManager(cacheManager);
            return em;
        }
    }

    /**
     * 返回配置文件流 避免ehcache配置文件一直被占用，无法完全销毁项目重新部署
     */
    protected InputStream getCacheManagerConfigFileInputStream() {
        String configFile = "classpath:ehcache/ehcache-shiro.xml";
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getInputStreamForPath(configFile);
            byte[] b = IOUtils.toByteArray(inputStream);
            InputStream in = new ByteArrayInputStream(b);
            return in;
        } catch (IOException e) {
            throw new ConfigurationException(
                    "Unable to obtain input stream for cacheManagerConfigFile [" + configFile + "]", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    /**
     * Cookie属性设置
     *
     * @return
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        //创建一个cookie，并设置key的值
        SimpleCookie cookie = new SimpleCookie("ZOHARINFO");
        //只允许http请求访问cookie
        cookie.setHttpOnly(true);
        //cookie过期时间，-1,存活一个会话，单位：秒，默认为-1
        cookie.setMaxAge(-1);
        // 设置Cookie的域名
//        cookie.setDomain(domain);
        // 设置cookie的有效访问路径
//        cookie.setPath(path);
        return cookie;
    }

    /**
     * 配置会话ID生成器
     *
     * @return
     */
    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /**
     * 配置Shiro监听器
     *
     * @return
     */
    @Bean(name = "shiroSessionListener")
    public MyShiroListener myShiroListener() {
        MyShiroListener shiroListener = new MyShiroListener();
        return shiroListener;
    }

    /**
     * 自定义sessionFactory会话
     */
    @Bean
    public MyShiroSessionFactory sessionFactory() {
        MyShiroSessionFactory sessionFactory = new MyShiroSessionFactory();
        return sessionFactory;
    }

    /**
     * 配置sessionDAO,自定义sessionDao
     *
     * @return
     */
    @Bean(name = "sessionDAO")
    public MySessionDao getMySessionDAO() {
        MySessionDao sessionDAO = new MySessionDao();

        return sessionDAO;
    }

    //配置shiro session 的一个管理器
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // 加入缓存管理器
        sessionManager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        sessionManager.setDeleteInvalidSessions(true);
        // 设置session过期时间
        sessionManager.setGlobalSessionTimeout(60 * 2 * 1000);
        // 去掉 JSESSIONID   JESSIONID放在url中，直接列在浏览器上
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        // 定义要使用的无效的Session定时调度器
//        sessionManager.setSessionValidationScheduler(SpringUtils.getBean(SpringSessionValidationScheduler.class));
        // 是否定时检查session
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        sessionManager.setSessionDAO(getMySessionDAO());
        // 自定义sessionFactory
        sessionManager.setSessionFactory(sessionFactory());
        return sessionManager;
    }

    /**
     * 配置安全管理器
     *
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置Realm
        defaultWebSecurityManager.setRealm(userRealm);
        // 注入缓存管理器;
        defaultWebSecurityManager.setCacheManager(getEhCacheManager());
        // session管理器
        defaultWebSecurityManager.setSessionManager(this.getDefaultWebSessionManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        System.out.println("shiro 过滤器");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/backstage/css/**", "anon");
//        filterChainDefinitionMap.put("/backstage/js/**", "anon");
//        filterChainDefinitionMap.put("/backstage/images/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/unauth", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");




        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }





}
