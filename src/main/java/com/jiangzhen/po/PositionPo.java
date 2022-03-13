package com.jiangzhen.po;

import lombok.Data;

@Data
public class PositionPo extends BasePo {

    /**
     * 岗位名称
     */
    private String positionName;

    /**
     * 描述
     */
    private String description;

    /**
     * 部门编号
     */
    private Long departmentId;
}
