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

    @GetMapping("/toPersonalReward")
    public String toPersonalReward(){
        return "personalReward.html";
    }

    @GetMapping("/toPersonalTrain")
    public String toPersonalTrain(){
        return "personalTrain.html";
    }

    @GetMapping("/toPersonalSalary")
    public String toPersonalSalary(){
        return "personalSalary.html";
    }

    @GetMapping("/toAttendance")
    public String toAttendance(){
        return "personalAttendance.html";
    }

    @GetMapping("/toLeave")
    public String toLeave(){
        return "personalLeave.html";
    }
}
