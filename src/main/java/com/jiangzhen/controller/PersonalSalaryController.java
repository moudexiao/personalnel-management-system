package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.po.PersonalSalary;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PersonalSalaryService;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.PositionVo;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.SalaryInput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class PersonalSalaryController {

    @Autowired
   private PersonalSalaryService personalSalaryService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/salary/list")
    @ResponseBody
    public ResultVo getList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                            @RequestParam(value = "size",defaultValue = "10") Integer size,
                            @RequestParam(value = "departmentName",required = false) String departmentName,
                            @RequestParam(value = "personalId",defaultValue = "0") Integer personalId,
                            @RequestParam(value = "year", defaultValue = "0") Integer year,
                            @RequestParam(value = "month", defaultValue = "0") Integer month){

        PageInfo<PersonalSalary> pageInfo = personalSalaryService.page(page, size, departmentName, personalId, year,month);
//        personalSalaryService.selectAll();
        return ResultVo.success(pageInfo);
    }

    @RequestMapping("/salary/add")
    @ResponseBody
    public ResultVo addInfo(@RequestBody @Valid SalaryInput input){

        PersonalVo personalVo = personalService.findById(input.getPersonalId());

        PersonalSalary personalSalary = new PersonalSalary();
        BeanUtils.copyProperties(input,personalSalary);


        DepartmentPo departmentName = departmentService.selectById(personalVo.getDepartmentId());
        PositionVo positionVo = positionService.selectById(personalVo.getPositionId());

        personalSalary.setDepartmentName(departmentName.getDepartmentName());
        personalSalary.setPersonalName(personalVo.getPersonalName());
        personalSalary.setPositionName(positionVo.getPositionName());


        BigDecimal bigDecimalSum = getBigDecimalSum(input.getBasisSalary(), input.getSubsidySalary(),
                input.getSocialSalary(), input.getProvidentFund(),
                input.getBonus());
        personalSalary.setAllSalary(bigDecimalSum);
        personalSalaryService.save(personalSalary);
        return ResultVo.success();
    }

    public static BigDecimal getBigDecimalSum(BigDecimal i, BigDecimal... arg) {
        BigDecimal sum = i;
        for (BigDecimal b : arg) {
            sum = sum.add(b);
        }
        return sum;
    }

    @RequestMapping("/salary/edit/{id}")
    @ResponseBody
    public ResultVo edit(@RequestBody @Valid SalaryInput input, @PathVariable(value = "id") Long id){

        //根据薪资id查询
        PersonalSalary personalSalary = personalSalaryService.selectById(id);
        //将输入复制到personalSalary中
        BeanUtils.copyProperties(input,personalSalary);

        //根据输入面板的personalId查询职员信息
        PersonalVo byId = personalService.findById(input.getPersonalId());

        personalSalary.setPersonalName(byId.getPersonalName());
        personalSalary.setDepartmentName(byId.getDepartmentName());
        personalSalary.setPositionName(byId.getPositionName());

        //更新信息
        personalSalaryService.update(personalSalary);
        return ResultVo.success();
    }

    @RequestMapping("/salary/delete")
    @ResponseBody
    public ResultVo batchDelete(@RequestBody List<Long> ids){
        personalSalaryService.batchDelete(ids);
        return ResultVo.success();
    }

    @GetMapping("/salary/all")
    @ResponseBody
    public ResultVo getAll(){
        personalSalaryService.selectAll();
        return ResultVo.success();
    }
}
