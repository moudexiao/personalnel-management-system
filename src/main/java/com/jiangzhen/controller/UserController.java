package com.jiangzhen.controller;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.common.utils.JWTUtils;
import com.jiangzhen.config.JWTToken;
import com.jiangzhen.enums.ResultEnum;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.RoleService;
import com.jiangzhen.service.UserService;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.RoleVo;
import com.jiangzhen.vo.UserVo;
import com.jiangzhen.vo.input.UserInput;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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
    @ResponseBody
//    @RequiresRoles("admin")
    public ResultVo add(@RequestBody @Valid UserInput input,HttpServletResponse response) {
        String salt = JWTUtils.getSalt();
        String password = new Md5Hash(input.getPassword(), salt, 100).toHex();
        UserPo user = new UserPo();
        BeanUtils.copyProperties(input, user);
        user.setSalt(salt);
        user.setPassword(password);
        userService.save(user);
        String token = JWTUtils.sign(user.getUsername(), password);
        return ResultVo.success();
    }

    @PostMapping("/user/edit/{id}")
//    @RequiresRoles("admin")
    @ResponseBody
    public ResultVo edit(@RequestBody @Valid UserInput input, @PathVariable(value = "id") Long id, HttpServletRequest request,HttpServletResponse response) {

        String salt = JWTUtils.getSalt();
        String password = new Md5Hash(input.getPassword(), salt, 100).toHex();
        UserPo user = userService.findById(id);
        BeanUtils.copyProperties(input, user);
        user.setSalt(salt);
        user.setPassword(password);
        userService.update(user);
        return ResultVo.success();
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

    @RequestMapping("/user/get")
    @ResponseBody
    public ResultVo getUser() {
        Subject subject = SecurityUtils.getSubject();
        UserPo userpo = (UserPo) subject.getPrincipal();
        return ResultVo.success(userpo.getUsername());
    }
    @RequestMapping("/user/list")
    @ResponseBody
    public ResultVo getUserList(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "size",defaultValue = "5") Integer size){
       PageInfo<UserVo> userPage = userService.page(page,size);
        return ResultVo.success(userPage);
    }

    @RequestMapping("/role/list")
    @ResponseBody
    public ResultVo getRoleName() {
        List<RoleVo> roleName = roleService.findAll();
        return ResultVo.success(roleName);
    }

    @RequestMapping("/user/delete")
    @ResponseBody
    public ResultVo batchDelete(@RequestBody List<Long> ids){
       userService.batchDelete(ids);
        return ResultVo.success();
    }
}
