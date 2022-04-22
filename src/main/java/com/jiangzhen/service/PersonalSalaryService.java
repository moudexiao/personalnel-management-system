package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalSalary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalSalaryService {
    /**
     * 分页查询
     */
    PageInfo<PersonalSalary> page(Integer page, Integer size, String departmentName, Integer personalId, Integer year,Integer month);

    /**
     * 添加
     * @param personalSalary
     * @return
     */
    int save(PersonalSalary personalSalary);

    /**
     * 修改
     * @param personalSalary
     * @return
     */
    int update(PersonalSalary personalSalary);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PersonalSalary selectById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(List<Long> ids);

    /**
     * 导出
     * @return
     */
    List<PersonalSalary> selectAll();
}
