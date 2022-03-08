package com.jiangzhen.service;

import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.vo.DepartmentVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    /**
     * 查询全部部门信息
     * @return
     */
    List<DepartmentVo> findAll();

    /**
     * 根据部门名称查询
     * @param departmentName
     * @return
     */
    int selectByDepartmentName(String departmentName);

    /**
     * 添加员工信息
     * @param department
     * @return
     */
    int save(DepartmentPo department);

    /**
     * 更新部门信息
     * @param department
     * @return
     */
    int update(DepartmentPo department);

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(List<Long> ids);
}
