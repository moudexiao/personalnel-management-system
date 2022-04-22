package com.jiangzhen.dao;

import com.jiangzhen.po.PersonalSalary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalSalaryDao {

    /**
     * 根据条件查询
     */
    List<PersonalSalary> selectByCondition(@Param("departmentName") String departmentName,
                                           @Param("personalId") Integer personalId,
                                           @Param("year") Integer year,
                                           @Param("month") Integer month);

    /**
     * 添加
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
   List<PersonalSalary>  selectAll();
}
