package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalPo;
import com.jiangzhen.vo.PersonalVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalService {
    /**
     * 添加员工信息
     * @param personal
     * @return
     */
    int save(PersonalPo personal);

    /**
     * 更新员工信息
     * @param personal
     * @return
     */
    int update(PersonalPo personal);

    /**
     * 根据id删除
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

    /**
     * 根据员工姓名查询信息
     * @param pname
     * @return
     */
    int selectByName(String pname);

    /**
     * 根据工作状态查询信息
     * @param workStatus
     * @return
     */
    int selectByStatus(String workStatus);

    /**
     * 查询全部信息
     * @return
     */
    List<PersonalVo> findAll();

    PageInfo<PersonalVo> page(Integer page, Integer size);

}
