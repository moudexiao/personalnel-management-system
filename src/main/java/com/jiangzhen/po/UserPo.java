package com.jiangzhen.po;

import lombok.Data;

/**
 * 用户表实体类
 */
@Data
public class UserPo extends BasePo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，md5
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 角色Id
     */
    private Long roleId;


}
