package com.jiangzhen.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PersonalTrainPo {

    /**
     * 培训id
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
     * 培训开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginDate;

    /**
     * 培训结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;

    /**
     * 培训内容
     */
    private String trainContent;

    /**
     * 培训分数
     */
    private BigDecimal trainScore;

    /**
     * 培训费用
     */
    private BigDecimal trainCost;

    /**
     * 备注
     */
    private String remake;
}
