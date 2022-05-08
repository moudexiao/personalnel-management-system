package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalLeavePo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalLeaveService {

    //分页查询
    PageInfo<PersonalLeavePo> page(Integer page, Integer size, String departmentName,
                                        Integer personalId, Integer year, Integer month);

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
