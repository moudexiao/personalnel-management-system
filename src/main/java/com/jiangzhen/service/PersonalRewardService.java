package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalRewardPo;
import com.jiangzhen.vo.PersonalRewardVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalRewardService {
    /**
     * 添加
     * @param reward
     * @return
     */
    int save(PersonalRewardPo reward);

    /**
     * 更新
     * @param reward
     * @return
     */
    int update(PersonalRewardPo reward);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteRewardById(Long id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    int batchDeleteReward(List<Long> ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PersonalRewardPo selectById(Long id);

    /**
     * 根据职位名称查询
     * @param rewardKind
     * @return
     */
    int selectByRewardKind(String rewardKind);

    /**
     * 查询全部
     * @return
     */
    List<PersonalRewardVo> selectAll();


    PageInfo<PersonalRewardVo> page(Integer page, Integer size, String departmentName, Integer personalId, Integer year, Integer month);
}
