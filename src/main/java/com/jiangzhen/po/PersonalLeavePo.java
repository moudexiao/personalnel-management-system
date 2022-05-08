package com.jiangzhen.po;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalLeavePo {

    private Long id;

    private Long personalId;

    private String personalName;

    private String departmentName;

    private String positionName;

    private Integer reason;

    private Date leaveTime;

    private Date expirationDate;

    private Integer status;
}
