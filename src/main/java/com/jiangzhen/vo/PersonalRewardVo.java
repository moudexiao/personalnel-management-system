package com.jiangzhen.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonalRewardVo implements Serializable {

    private static final long serialVersionUID = -990914614010626754L;
    private Long id;

    private Long personalId;

    private String personalName;

    private String departmentName;

    private String positionName;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date rewardDate;

    private Long rewardKind;

    private Long rewardAmount;

    private String description;
}
