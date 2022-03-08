package com.jiangzhen.vo.input;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class UserInput {

    @NotBlank(message = "用户名必填")
    private String username;

    @NotBlank(message = "密码必填")
    private String password;

    private Long roleId;
    private String code;
    private String timestamp;
}