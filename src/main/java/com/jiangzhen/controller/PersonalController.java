package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping("/personal/list")
    @ResponseBody
    public ResultVo getPersonalList(@RequestParam(value = "page",defaultValue = "1") Integer page,@RequestParam(value = "size",defaultValue = "10") Integer size,
                                    @RequestParam(value = "departmentId", defaultValue = "0") Long departmentId, @RequestParam(value = "personalName", required = false) String personalName,
                                    @RequestParam(value = "workStatus", defaultValue = "0") Long workStatus){
            PageInfo<PersonalVo> personalVos= personalService.page(page,size);
            return ResultVo.success(personalVos);
    }
}
