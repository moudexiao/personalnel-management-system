package com.jiangzhen.dao;

import com.jiangzhen.po.PersonalAttendancePo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalAttendanceDao {

    /**
     * 分页查询
     * @param departmentName
     * @param personalId
     * @param year
     * @param month
     * @return
     */
    List<PersonalAttendancePo> selectByCondition(@Param("departmentName") String departmentName,
                                                 @Param("personalId") Integer personalId,
                                                 @Param("year") Integer year,
                                                 @Param("month") Integer month);

    /**
     * 查询全部
     * @return
     */
    List<PersonalAttendancePo> selectAll();

    /**
     * 添加
     * @param personalAttendancePo
     * @return
     */
    int save(PersonalAttendancePo personalAttendancePo);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PersonalAttendancePo selectById(Long id);

    /**
     * 更新数据
     * @param personalAttendancePo
     * @return
     */
    int update(PersonalAttendancePo personalAttendancePo);

    /**
     * 删除
     * @param ids
     * @return
     */
    int batchDelete(List<Long> ids);
}
