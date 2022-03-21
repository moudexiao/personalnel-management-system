package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalTrainPo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PersonalTrainService {

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
     * 分页
     * @param page
     * @param size
     * @param departmentName
     * @param personalId
     * @param beginDate
     * @param endDate
     * @return
     */
     PageInfo<PersonalTrainPo> page(Integer page,Integer size,String departmentName, Integer personalId, Date beginDate, Date endDate);

    PersonalTrainPo selectById(Long id);
}
