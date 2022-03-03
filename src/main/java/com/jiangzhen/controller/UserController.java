package com.jiangzhen.controller;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.jiangzhen.common.utils.JWTUtils;
import com.jiangzhen.config.JWTToken;
import com.jiangzhen.enums.ResultEnum;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.UserInput;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 验证码过期时间 60s
     */
    private static TimedCache<String, String> cache = CacheUtil.newTimedCache(60 * 1000);

    @RequestMapping("/user/toLogin")
    public String toLogin(){
        return "login.html";
    }

    @PostMapping("/user/login")
    @ResponseBody
    public ResultVo login(@RequestBody @Valid UserInput input, HttpServletResponse response) {
        String timestamp = input.getTimestamp();
        if (ObjectUtils.isEmpty(timestamp) || ObjectUtils.isEmpty(input.getCode())) {
            return ResultVo.fail(ResultEnum.CODE_NOT_EXIST);
        }
        if (ObjectUtils.isEmpty(cache.get(timestamp))) {
            return ResultVo.fail(ResultEnum.CODE_FAIL);
        }
        String code = cache.get(timestamp);
        if (!code.equals(input.getCode())) {
            return ResultVo.fail(ResultEnum.CODE_FAIL);
        }
        String username = input.getUsername();
        String password = input.getPassword();
        UserPo user = userService.findByUsername(username);
        String salt = user.getSalt();
        Md5Hash md5Hash = new Md5Hash(password, salt, 100);
        //生成token
        String token = JWTUtils.sign(username, md5Hash.toHex());
        //登录
        SecurityUtils.getSubject().login(new JWTToken(token));
        //把token放到浏览器cookie里面去
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(60*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResultVo.success(token);
    }

    @PostMapping("/user/register")
//    @RequiresRoles("admin")
    public ResultVo add(@RequestBody @Valid UserInput input) {
        String salt = JWTUtils.getSalt();
        String password = new Md5Hash(input.getPassword(), salt, 100).toHex();
        UserPo user = new UserPo();
        BeanUtils.copyProperties(input, user);
        user.setSalt(salt);
        user.setPassword(password);
        userService.save(user);
        String token = JWTUtils.sign(user.getUsername(), password);
        return ResultVo.success(token);
    }

    @GetMapping("/image")
    public void code(@RequestParam(value = "timestamp") String timestamp, HttpServletResponse response)
            throws IOException {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 20);
        lineCaptcha.write(response.getOutputStream());
        cache.put(timestamp, lineCaptcha.getCode());
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "index.html";
    }

}
