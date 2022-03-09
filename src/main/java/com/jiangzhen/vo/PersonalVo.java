package com.jiangzhen.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PersonalVo implements Serializable {
    private static final long serialVersionUID = -3362096141131407416L;
    private Long id;
    private String personalName;
    private Long gender;
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date birthday;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
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
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            timezone = "GMT+8"
    )
    private Date beginDate;
}
