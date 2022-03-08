package com.jiangzhen.service;

import com.jiangzhen.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {


    /**
     * 查询角色名称
     * @return
     */
    List<RoleVo> findAll();
}
