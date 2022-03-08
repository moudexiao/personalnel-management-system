package com.jiangzhen.dao;

import com.jiangzhen.po.RolePo;
import com.jiangzhen.vo.RoleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Role dao层接口
 */
@Repository
public interface RoleDao {

    int save(RolePo rolePo);

    int update(RolePo rolePo);

    int deleteById(Long id);

    int batchDelete(List<Long> ids);

    RolePo findById(Long id);

    List<RoleVo> findAll();

}