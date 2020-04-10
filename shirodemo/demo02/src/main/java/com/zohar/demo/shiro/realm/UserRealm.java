package com.zohar.demo.shiro.realm;

import com.zohar.demo.pojo.SysPermission;
import com.zohar.demo.pojo.SysRole;
import com.zohar.demo.pojo.SysUser;
import com.zohar.demo.service.SysPermissionService;
import com.zohar.demo.service.SysRoleService;
import com.zohar.demo.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.servlet.ApplicationContextRequestMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private SysRoleService roleService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPermissionService permissonService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        SpringBeanPreparerFactory.getApplicationContext().getBean();
        System.out.println("UserRealm.doGetAuthorizationInfo");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        System.out.println("SysUser:\t" + user);

        Set<String> roles = new HashSet<String>();
        roles = roleService.getUserRoleByUserId(user.getId());

        Set<String> permissions = new HashSet<String>();
        permissions = permissonService.getUserPermissionByUserId(user.getId());

        System.out.println("**************************roles**************************");
        for (String tmp:
             roles) {
            System.out.println(tmp);
        }
        System.out.println("**************************permissions**************************");
        for (String tmp:
             permissions){
            System.out.println(tmp);
        }

        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     * 验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("UserRealm.doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        String password = "";
        if (token.getPassword() != null) {
            password = new String(token.getPassword());
            System.out.println("*******************" + username);
        }

        SysUser user = user = userService.login(username, password);

        if (null == user) {
            throw new AccountException("账号或密码错误");
        }
        System.out.println("登录成功");
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
