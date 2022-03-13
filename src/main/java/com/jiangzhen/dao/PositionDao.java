package com.jiangzhen.dao;

import com.jiangzhen.po.PositionPo;
import com.jiangzhen.vo.PositionVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionDao {

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
    int selectById(Long id);

    /**
     * 根据职位名称查询
     * @param positionName
     * @return
     */
    int selectByPositionName(String positionName);

    /**
     * 查询全部
     * @return
     */
    List<PositionVo> selectAll();
}