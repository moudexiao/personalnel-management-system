package com.jiangzhen.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class BasePo implements Serializable {

    private static final long serialVersionUID = -4337096965231088641L;
    private Long id;

    private Date createTime;

    private Date updateTime;

    /**
     * 重写object类 equals方法
     * @param po
     * @return
     */
    @Override
    public boolean equals(Object po) {
        if (!(po instanceof BasePo)) {
            return false;
        }
        if (((BasePo) po).getId() == null || this.getId() == null) {
            return false;
        }
        return this.getId().longValue() == ((BasePo) po).getId().longValue();
    }

    /**
     * 重写equals 必须要重写 hashcode方法
     * @return
     */
    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
