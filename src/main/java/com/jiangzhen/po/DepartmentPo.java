package com.jiangzhen.po;

import lombok.Data;

@Data
public class DepartmentPo extends BasePo{
    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 描述
     */
    private String description;
}
