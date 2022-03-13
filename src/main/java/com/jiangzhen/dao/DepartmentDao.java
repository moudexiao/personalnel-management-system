package com.jiangzhen.dao;

import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.vo.DepartmentVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao {
    //添加部门
    int save(DepartmentPo department);

    //更新部门信息
    int update(DepartmentPo department);

    //根据id删除部门信息
    int deleteById(Long id);

    //批量删除部门
    int batchDelete(List<Long> ids);

    //根据部门名称查询
    DepartmentVo selectByDepartmentName(String departmentName);

    DepartmentPo selectById(Long id);

    //查询全部信息
    List<DepartmentVo> findAll();

}
