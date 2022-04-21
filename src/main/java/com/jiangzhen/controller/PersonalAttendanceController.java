package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.po.PersonalAttendancePo;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PersonalAttendanceService;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.PositionVo;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.AttendanceInput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonalAttendanceController {

    @Autowired
    private PersonalAttendanceService personalAttendanceService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/attendance/list")
    @ResponseBody
    public ResultVo getRewardList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "departmentName", required = false) String departmentName,
                                  @RequestParam(value = "personalId", defaultValue = "0") Integer personalId,
                                  @RequestParam(value = "year", defaultValue = "0") Integer year,
                                  @RequestParam(value = "month", defaultValue = "0") Integer month){
        PageInfo<PersonalAttendancePo> pageInfo = personalAttendanceService.page(page, size, departmentName, personalId, year, month);
        personalAttendanceService.selectAll();
        return ResultVo.success(pageInfo);
    }

    @RequestMapping("/attendance/add")
    @ResponseBody
    public ResultVo add(@RequestBody @Valid AttendanceInput input){
        PersonalAttendancePo attendancePo = new PersonalAttendancePo();

        BeanUtils.copyProperties(input,attendancePo);

        PersonalVo personalVo = personalService.findById(input.getPersonalId());

        DepartmentPo departmentPo = departmentService.selectById(personalVo.getDepartmentId());

        PositionVo positionVo = positionService.selectById(personalVo.getPositionId());

        attendancePo.setPersonalName(personalVo.getPersonalName());
        attendancePo.setDepartmentName(departmentPo.getDepartmentName());
        attendancePo.setPositionName(positionVo.getPositionName());

        personalAttendanceService.save(attendancePo);
        return ResultVo.success();
    }

    @RequestMapping("/attendance/edit/{id}")
    @ResponseBody
    public ResultVo edit(@RequestBody @Valid AttendanceInput input, @PathVariable(value = "id") Long id){
       PersonalAttendancePo attendancePo = personalAttendanceService.selectById(id);
        BeanUtils.copyProperties(input,attendancePo);

        PersonalVo personalVo = personalService.findById(input.getPersonalId());

        DepartmentPo departmentPo = departmentService.selectById(personalVo.getDepartmentId());

        PositionVo positionVo = positionService.selectById(personalVo.getPositionId());

        attendancePo.setPersonalName(personalVo.getPersonalName());
        attendancePo.setDepartmentName(departmentPo.getDepartmentName());
        attendancePo.setPositionName(positionVo.getPositionName());

        personalAttendanceService.update(attendancePo);
        return ResultVo.success();
    }

    @RequestMapping("/attendance/delete")
    @ResponseBody
    public ResultVo batchDelete(@RequestBody List<Long> ids){
        personalAttendanceService.batchDelete(ids);
        return ResultVo.success();
    }

    @GetMapping("/attendance/all")
    @ResponseBody
    public ResultVo getAll(){
        return ResultVo.success(personalAttendanceService.selectAll());
    }
}
