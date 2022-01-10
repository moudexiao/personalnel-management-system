package com.jiangzhen.po;

import lombok.Data;

import java.util.Date;

/**
 * @author: zhaoyiming
 * @date: 2022-01-04 17:04
 */
@Data
public class UserPo extends BasePo {

    /**
     * 工号
     */
    private String jobNo;

    /**
     * 所属部门
     */
    private Integer department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 手机号码
     */
    private String cellphone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码，md5
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

}
