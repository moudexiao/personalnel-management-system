package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalTrainDao;
import com.jiangzhen.po.PersonalTrainPo;
import com.jiangzhen.service.PersonalTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonalTrainServiceImpl implements PersonalTrainService {

    @Autowired
    private PersonalTrainDao personalTrainDao;
    @Override
    public int save(PersonalTrainPo personalTrainPo) {
        return personalTrainDao.save(personalTrainPo);
    }

    @Override
    public int update(PersonalTrainPo personalTrainPo) {
        return personalTrainDao.update(personalTrainPo);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return personalTrainDao.batchDelete(ids);
    }

    @Override
    public List<PersonalTrainPo> selectAll() {
        return personalTrainDao.selectAll();
    }

    @Override
    public PageInfo<PersonalTrainPo> page(Integer page, Integer size, String departmentName, Integer personalId, Date beginDate, Date endDate) {
        PageHelper.startPage(page,size);
        PageInfo<PersonalTrainPo> pageInfo = new PageInfo<>(personalTrainDao.selectByCondition(departmentName,personalId,beginDate,endDate));
        return pageInfo;
    }

    @Override
    public PersonalTrainPo selectById(Long id) {
        return personalTrainDao.selectById(id);
    }

}
