package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalPo;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.PersonalInput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonalController {

    @Autowired
    private PersonalService personalService;




    @GetMapping("/personal/list")
    @ResponseBody
    public ResultVo getPersonalList(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "size",defaultValue = "10") Integer size,
                                    @RequestParam(value = "departmentId", defaultValue = "0") Long departmentId, @RequestParam(value = "personalName", required = false) String personalName,
                                    @RequestParam(value = "workStatus", defaultValue = "0") Long workStatus){
            PageInfo<PersonalVo> personalVos= personalService.page(page,size);
            personalService.findAll();
            return ResultVo.success(personalVos);
    }

    @RequestMapping("/personal/delete")
    @ResponseBody
    public ResultVo batchDelete(@RequestBody List<Long> ids){
        personalService.batchDelete(ids);
        return ResultVo.success();
    }

    @RequestMapping("/personal/edit/{id}")
    @ResponseBody
    public ResultVo edit(@RequestBody @Valid PersonalInput input, @PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response){
        PersonalPo personal = personalService.findById(id);
        BeanUtils.copyProperties(input, personal);
        personalService.update(personal);
        return ResultVo.success();
    }

    @RequestMapping("/personal/add")
    @ResponseBody
    public ResultVo add(@RequestBody @Valid PersonalInput input,HttpServletResponse response){
        PersonalPo personalPo = new PersonalPo();
        BeanUtils.copyProperties(input, personalPo);
        personalService.save(personalPo);
        return ResultVo.success();
    }
}
