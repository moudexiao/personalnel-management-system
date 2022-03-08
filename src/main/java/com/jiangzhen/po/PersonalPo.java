package com.jiangzhen.po;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalPo extends BasePo{

    /**
     * 员工姓名
     */
    private String pname;

    /**
     * 性别1：男，2：女
     */
    private int gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 联系电话
     */
    private Long phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 身份证号
     */
    private Long identity;

    /**
     * 学历
     */
    private String education;

    /**
     * 毕业学校
     */
    private String school;

    /**
     * 照片url
     */
    private String imgUrl;

    /**
     * 工作状态：1:正式 2:试用 3:实习 4:离职
     */
    private int workStatus;

    /**
     * 部门id
     */
    private Integer departmentId;

    /**
     * 职位id
     */
    private Integer positionId;

    /**
     * 入职时间
     */
    private Date beginDate;


}