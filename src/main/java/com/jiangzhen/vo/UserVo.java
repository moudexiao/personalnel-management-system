package com.jiangzhen.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 7567254876096806136L;

    private Integer id;
    private Date createTime;
    private Date updateTime;
    private String username;
    private Integer roleId;
    private String roleDescription;
}
