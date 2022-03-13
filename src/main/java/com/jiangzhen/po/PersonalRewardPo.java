package com.jiangzhen.po;

import lombok.Data;

import java.util.Date;


@Data
public class PersonalRewardPo {

    /**
     * 奖惩id
     */
    private Long id;

    /**
     * 员工id
     */
    private Long personalId;

    /**
     * 员工姓名
     */
    private String personalName;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 日期
     */
    private Date rewardDate;

    /**
     * 奖罚种类 1：奖励 2：惩罚
     */
    private Long rewardKind;

    /**
     * 金额
     */
    private Long rewardAmount;

    /**
     * 奖罚描述
     */
    private String description;
}
