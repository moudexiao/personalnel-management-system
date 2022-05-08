package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.po.PersonalLeavePo;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PersonalLeaveService;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.PositionVo;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.LeaveInput;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PersonalLeaveController {

    @Autowired
    private PersonalLeaveService personalLeaveService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/leave/list")
    @ResponseBody
    public ResultVo getLeaveList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "departmentName", required = false) String departmentName,
                                  @RequestParam(value = "personalId", defaultValue = "0") Integer personalId,
                                  @RequestParam(value = "year", defaultValue = "0") Integer year,
                                  @RequestParam(value = "month", defaultValue = "0") Integer month){
        PageInfo<PersonalLeavePo> pageInfo = personalLeaveService.page(page, size, departmentName, personalId, year, month);
        return ResultVo.success(pageInfo);
    }

    //TODO 发起请假,
    // input里面只有请假起始时间,此接口的入口在登录首页,供员工权限用户发起请求,
    // user表里增加了personal_id,使员工权限用户可以绑定员工档案信息,
    // shiro框架获取到当前登录用户信息后,便可以根据personal_id定位到用户绑定的员工档案信息,而无需前端传入
    @RequestMapping("/leave/add")
    @ResponseBody
    public ResultVo add(@RequestBody @Valid LeaveInput input){

        UserPo user = (UserPo) SecurityUtils.getSubject().getPrincipal();

        PersonalLeavePo leavePo = new PersonalLeavePo();

        BeanUtils.copyProperties(input,leavePo);

        PersonalVo personalVo = personalService.findById(user.getPersonalId());

        DepartmentPo departmentPo = departmentService.selectById(personalVo.getDepartmentId());

        PositionVo positionVo = positionService.selectById(personalVo.getPositionId());

        leavePo.setPersonalId(personalVo.getId());
        leavePo.setPersonalName(personalVo.getPersonalName());
        leavePo.setDepartmentName(departmentPo.getDepartmentName());
        leavePo.setPositionName(positionVo.getPositionName());

        personalLeaveService.save(leavePo);
        return ResultVo.success();
    }

    /**
     * 修改状态(通过/拒绝)
     * @param
     * @param id
     * @return
     */
    @RequestMapping("/leave/review/{id}/{status}")
    @ResponseBody
    public ResultVo review(@PathVariable(value = "status")  Integer status, @PathVariable(value = "id") Long id){
       PersonalLeavePo leavePo = personalLeaveService.selectById(id);
       leavePo.setStatus(status);
        personalLeaveService.update(leavePo);
        return ResultVo.success();
    }



    @GetMapping("/leave/all")
    @ResponseBody
    public ResultVo getAll(){
        return ResultVo.success(personalLeaveService.selectAll());
    }
}
