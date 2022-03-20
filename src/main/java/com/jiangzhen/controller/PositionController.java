package com.jiangzhen.controller;

import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PositionVo;
import com.jiangzhen.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/position/select/{departmentId}")
    @ResponseBody
    public ResultVo getPosition(@RequestBody @PathVariable(value = "departmentId") Long departmentId){
        List<PositionVo> positionVo =  positionService.selectAll(departmentId);
        return ResultVo.success(positionVo);
    }
}
