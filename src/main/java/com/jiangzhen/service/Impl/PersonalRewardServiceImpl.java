package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalRewardDao;
import com.jiangzhen.po.PersonalRewardPo;
import com.jiangzhen.service.PersonalRewardService;
import com.jiangzhen.vo.PersonalRewardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalRewardServiceImpl implements PersonalRewardService {

    @Autowired
    private PersonalRewardDao rewardDao;

    @Override
    public int save(PersonalRewardPo reward) {
        return rewardDao.save(reward);
    }

    @Override
    public int update(PersonalRewardPo reward) {
        return rewardDao.update(reward);
    }

    @Override
    public int deleteRewardById(Long id) {
        return rewardDao.deleteRewardById(id);
    }

    @Override
    public int batchDeleteReward(List<Long> ids) {
        return rewardDao.batchDeleteReward(ids);
    }

    @Override
    public PersonalRewardPo selectById(Long id) {
        return rewardDao.selectById(id);
    }

    @Override
    public int selectByRewardKind(String rewardKind) {
        return rewardDao.selectByRewardKind(rewardKind);
    }

    @Override
    public List<PersonalRewardVo> selectAll() {
        return rewardDao.selectAll();
    }

    @Override
    public PageInfo<PersonalRewardVo> page(Integer page, Integer size, String departmentName, Integer personalId, Integer year, Integer month) {
        PageHelper.startPage(page,size);
        PageInfo<PersonalRewardVo> pageInfo = new PageInfo<>(rewardDao.selectByCondition(departmentName,personalId,year,month));
        return pageInfo;
    }

}
