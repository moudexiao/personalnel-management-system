package com.jiangzhen.dao;

import com.jiangzhen.po.PersonalLeavePo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalLeaveDao {

    /**
     * 分页查询
     * @param departmentName
     * @param personalId
     * @param year
     * @param month
     * @return
     */
    List<PersonalLeavePo> selectByCondition(@Param("departmentName") String departmentName,
                                            @Param("personalId") Integer personalId,
                                            @Param("year") Integer year,
                                            @Param("month") Integer month);

    /**
     * 查询全部
     * @return
     */
    List<PersonalLeavePo> selectAll();

    /**
     * 添加
     * @param
     * @return
     */
    int save(PersonalLeavePo personalLeavePo);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PersonalLeavePo selectById(Long id);

    /**
     * 更新数据
     * @param
     * @return
     */
    int update(PersonalLeavePo personalLeavePo);
}
