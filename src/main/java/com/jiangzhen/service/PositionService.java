package com.jiangzhen.service;

import com.jiangzhen.po.PositionPo;
import com.jiangzhen.vo.PositionVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {
    /**
     * 添加
     * @param position
     * @return
     */
    int save(PositionPo position);

    /**
     * 更新
     * @param position
     * @return
     */
    int update(PositionPo position);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deletePositionById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDeletePosition(List<Long> ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PositionVo selectById(Long id);

    /**
     * 根据职位名称查询
     * @param positionName
     * @return
     */
    PositionVo selectByPositionName(String positionName);

    /**
     * 根据部门id查询
     * @param departmentId
     * @return
     */
    List<PositionVo> selectAll(Long departmentId);

    PositionVo selectByDepartmentId(Long departmentId);
}
