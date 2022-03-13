package com.jiangzhen.controller;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.po.PersonalRewardPo;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PersonalRewardService;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.vo.PersonalRewardVo;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.ResultVo;
import com.jiangzhen.vo.input.RewardInput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonalRewardController {

    @Autowired
    private PersonalRewardService personalRewardService;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/reward/list")
    @ResponseBody
    public ResultVo getRewardList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "departmentName", required = false) String departmentName,
                                  @RequestParam(value = "personalId", defaultValue = "0") Long personalId,
                                  @RequestParam(value = "year", defaultValue = "0") Integer year,
                                  @RequestParam(value = "month", defaultValue = "0") Integer month){
        PageInfo<PersonalRewardVo> personalRewardVoPageInfo= personalRewardService.page(page,size);
        personalRewardService.selectAll();
//        personalRewardService.selectById(personalId);
        return ResultVo.success(personalRewardVoPageInfo);
    }

    @RequestMapping("/reward/delete")
    @ResponseBody
    public ResultVo delete(@RequestBody List<Long> ids){
        personalRewardService.batchDeleteReward(ids);
        return ResultVo.success();
    }
    @RequestMapping("/reward/add")
    @ResponseBody
    public ResultVo add(@RequestBody @Valid RewardInput input){
        PersonalRewardPo rewardPo = new PersonalRewardPo();

        BeanUtils.copyProperties(input,rewardPo);

       PersonalVo personalVo = personalService.findById(input.getPersonalId());

        DepartmentPo departmentName = departmentService.selectById(personalVo.getDepartmentId());

        rewardPo.setDepartmentName(departmentName.getDepartmentName());
        rewardPo.setPersonalName(personalVo.getPersonalName());
//

//       System.out.println(departmentName);
//
//       BeanUtils.copyProperties(departmentName,input);

        personalRewardService.save(rewardPo);
        return ResultVo.success();
    }

    @RequestMapping("/reward/edit/{id}")
    @ResponseBody
    public ResultVo edit(@RequestBody @Valid RewardInput input, @PathVariable(value = "id") Long id,HttpServletRequest request, HttpServletResponse response){
        PersonalRewardPo reward = personalRewardService.selectById(id);
//        System.out.println(reward);
        BeanUtils.copyProperties(input, reward);

        PersonalVo personalVo = personalService.findById(input.getPersonalId());

        reward.setPersonalName(personalVo.getPersonalName());
        reward.setDepartmentName(personalVo.getDepartmentName());
        reward.setPositionName(personalVo.getPositionName());
//        System.out.println(reward);
        personalRewardService.update(reward);
//        System.out.println(reward);
        return ResultVo.success();
    }
}
