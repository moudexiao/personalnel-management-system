package com.jiangzhen.dao;

import com.jiangzhen.po.PersonalTrainPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PersonalTrainDao {

    /**
     * 添加培训信息
     * @param personalTrainPo
     * @return
     */
    int save(PersonalTrainPo personalTrainPo);

    /**
     * 更新信息
     * @param personalTrainPo
     * @return
     */
    int update(PersonalTrainPo personalTrainPo);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDelete(List<Long> ids);


    /**
     * 查询全部
     * @return
     */
    List<PersonalTrainPo> selectAll();

    /**
     * 条件查询
     * @param departmentName
     * @param personalId
     * @param beginDate
     * @param endDate
     * @return
     */
    List<PersonalTrainPo> selectByCondition(@Param("departmentName") String departmentName,
                                            @Param("personalId") Integer personalId,
                                            @Param("beginDate")Date beginDate,
                                            @Param("endDate")Date endDate);

    PersonalTrainPo selectById(Long id);
}
