package com.jiangzhen.po;

import lombok.Data;

/**
 * 角色实体类
 */

@Data
public class RolePo extends BasePo {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 权限
     */
    private String permission;

}