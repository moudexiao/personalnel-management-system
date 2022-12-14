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
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    //TODO ????????????,
    // input??????????????????????????????,?????????????????????????????????,?????????????????????????????????,
    // user???????????????personal_id,???????????????????????????????????????????????????,
    // shiro??????????????????????????????????????????,???????????????personal_id??????????????????????????????????????????,?????????????????????
    @RequestMapping("/leave/add")
    @ResponseBody
    public ResultVo add(@RequestBody @Valid LeaveInput input){

        UserPo user = (UserPo) SecurityUtils.getSubject().getPrincipal();
        System.out.println(user);
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
     * ????????????(??????/??????)
     * @param
     * @param id
     * @return
     */
    @RequestMapping("/leave/review/{id}/{status}")
    @ResponseBody
    @RequiresRoles(value = {"?????????","????????????", "????????????"},logical = Logical.OR)
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
