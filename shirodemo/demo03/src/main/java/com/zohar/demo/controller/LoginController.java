package com.zohar.demo.controller;

import com.zohar.demo.service.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private SysRoleService roleService;

    @PostMapping("login")
    @ResponseBody
    public String toLogin(String email, String pswd, Model model){
        UsernamePasswordToken token = new UsernamePasswordToken(email, pswd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println("2222");
            model.addAttribute("msg","登录成功");
            return "index";
        }catch (AuthenticationException e){
            model.addAttribute("msg","登录失败");
            return "login";
        }
    }

    @GetMapping("login")
    public String login(Model model){
        return "login";
    }

    @PostMapping("/logout")
    public String logout(Model model){
        SecurityUtils.getSubject().logout();
        model.addAttribute("msg","退出成功");
        return "login";
    }

    @RequestMapping("/unauth")
    public String unauth(Model model){
        SecurityUtils.getSubject().logout();
        model.addAttribute("msg","未授权");
        return "unauth";
    }


    @GetMapping("/index")
    @RequiresPermissions(value = {"system:index"})
    public String index(Model model){
        model.addAttribute("msg","这里是首页");
        return "index";
    }

    @GetMapping("/page1")
    @RequiresPermissions("page:page1")
    public String page1(Model model){
        model.addAttribute("msg","界面1");
        return "page1";
    }
    @GetMapping("/page2")
    @RequiresPermissions("page:page2")
    public String page2(Model model){
        model.addAttribute("msg","界面2");
        return "page2";
    }


}
