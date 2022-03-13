package com.jiangzhen.vo.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PersonalInput {
    @NotBlank(message = "姓名必填")
    private String personalName;
    @NotNull(message = "性别必填")
    private Long gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private Long phone;
    private String email;
    private Long identity;
    private String education;
    private String school;
    private String imgUrl;
    private Long workStatus;
    private Long departmentId;
    private String departmentName;
    private Long positionId;
    private Date beginDate;
}
