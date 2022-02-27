package com.jiangzhen.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoleVo implements Serializable {


    private static final long serialVersionUID = -4692180942231836157L;
    private Integer id;
    private Date createTime;
    private Date updateTime;
    private String name;
    private String description;
    private String permission;
}
