package com.jiangzhen.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zhaoyiming
 * @date: 2022-02-11 16:48
 */
@Controller
public class DemoController {

    @RequestMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("msg", "shiro study");
        return "index";
    }

    @RequestMapping("/user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/login";
    }


    @RequestMapping("login")
    public String login(String username, String password, Model model) {
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录操作 所有登录步骤都交给shiro来执行
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "用户名不存在");
            return "user/login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误");
            return "user/login";
        }
    }

    @RequestMapping("/noAuth")
    @ResponseBody
    public String noAuth(){
        return "未授权跳转页面";
    }

}
