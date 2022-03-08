package com.jiangzhen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/toWelcome")
    public String toWelcome(){
        return "welcome.html";
    }

    @GetMapping("/toUserList")
    public String toUserList(){
        return "userList.html";
    }
    @GetMapping("/toPersonal")
    public String toPersonal(){
        return "personal.html";
    }
}
