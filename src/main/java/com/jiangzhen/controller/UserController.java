package com.jiangzhen.controller;

import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaoyiming
 * @date: 2022-01-07 11:20
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public void addUser(){
        UserPo userPo = new UserPo();
        userPo.setName("zym");
        userService.addUser(userPo);
    }
}
