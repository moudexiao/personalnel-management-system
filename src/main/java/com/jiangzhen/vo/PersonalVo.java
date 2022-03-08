package com.jiangzhen.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class PersonalVo implements Serializable {
    private static final long serialVersionUID = -3362096141131407416L;
    private Integer id;
    private String pname;
    private Integer gender;
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date birthday;
    private Long phone;
    private String email;
    private Long identity;
    private String education;
    private String school;
    private String imgUrl;
    private Long workStatus;
    private Long departmentId;
    private Long positionId;
    private String departmentName;
    private String positionName;
    private Date beginDate;
}
