package com.jiangzhen.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PersonalSalary {

    /**
     * 薪资编号
     */
    private Long id;

    private Long personalId;

    private String personalName;

    private String departmentName;

    private String positionName;

    private Date salaryDate;

    private BigDecimal basisSalary;

    private BigDecimal subsidySalary;

    private BigDecimal socialSalary;

    private BigDecimal providentFund;

    private BigDecimal bonus;

    private BigDecimal allSalary;

}
