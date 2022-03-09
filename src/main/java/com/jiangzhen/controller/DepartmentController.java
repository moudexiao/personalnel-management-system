package com.jiangzhen.controller;

import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.vo.DepartmentVo;
import com.jiangzhen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
   private DepartmentService departmentService;

    @GetMapping("/department/select")
    @ResponseBody
    public ResultVo departmentSelect(){
       List<DepartmentVo> departmentNames =  departmentService.findAll();
       System.out.println(departmentNames);
        return ResultVo.success(departmentNames);
    }
}
