package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.po.PersonalTrainPo;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.service.PersonalTrainService;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.PositionVo;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.PersonalTrainInput;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class PersonalTrainController {

    @Autowired
    private PersonalTrainService personalTrainService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/train/list")
    @ResponseBody
    public ResultVo getList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                            @RequestParam(value = "size",defaultValue = "10") Integer size,
                            @RequestParam(value = "departmentName",required = false) String departmentName,
                            @RequestParam(value = "personalId",defaultValue = "0") Integer personalId,
                            @RequestParam(value = "beginDate",required = false)Date beginDate,
                            @RequestParam(value = "endDate",required = false)Date endDate){

        PageInfo<PersonalTrainPo> pageInfo = personalTrainService.page(page, size, departmentName, personalId, beginDate, endDate);
        personalTrainService.selectAll();
        return ResultVo.success(pageInfo);
    }

    @RequestMapping("/train/add")
    @ResponseBody
    @RequiresRoles(value = {"管理员","部门经理", "部门主管"},logical = Logical.OR)
    public ResultVo addInfo(@RequestBody @Valid PersonalTrainInput input){
        PersonalTrainPo personalTrainPo = new PersonalTrainPo();
        BeanUtils.copyProperties(input,personalTrainPo);
        PersonalVo personalVo = personalService.findById(input.getPersonalId());

        DepartmentPo departmentName = departmentService.selectById(personalVo.getDepartmentId());
        PositionVo positionVo = positionService.selectById(personalVo.getPositionId());

        personalTrainPo.setDepartmentName(departmentName.getDepartmentName());
        personalTrainPo.setPersonalName(personalVo.getPersonalName());
        personalTrainPo.setPositionName(positionVo.getPositionName());

        personalTrainService.save(personalTrainPo);
        return ResultVo.success();
    }

    @RequestMapping("/train/edit/{id}")
    @ResponseBody
    @RequiresRoles(value = {"管理员","部门经理", "部门主管"},logical = Logical.OR)
    public ResultVo edit(@RequestBody @Valid PersonalTrainInput input,@PathVariable(value = "id") Long id){

        PersonalTrainPo personalTrainPo = personalTrainService.selectById(id);
        BeanUtils.copyProperties(input, personalTrainPo);

        PersonalVo personalVo = personalService.findById(input.getPersonalId());

        personalTrainPo.setPersonalName(personalVo.getPersonalName());
        personalTrainPo.setDepartmentName(personalVo.getDepartmentName());
        personalTrainPo.setPositionName(personalVo.getPositionName());
        personalTrainService.update(personalTrainPo);
        return ResultVo.success();
    }

    @RequestMapping("/train/delete")
    @ResponseBody
    @RequiresRoles(value = {"管理员","部门经理", "部门主管"},logical = Logical.OR)
    public ResultVo delete(@RequestBody List<Long> ids){
        personalTrainService.batchDelete(ids);
        return ResultVo.success();
    }

    @RequestMapping("/train/all")
    @ResponseBody
    public ResultVo getAll(){
        ;
        return ResultVo.success(personalTrainService.selectAll());
    }
}
