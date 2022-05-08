package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalLeaveDao;
import com.jiangzhen.po.PersonalLeavePo;
import com.jiangzhen.service.PersonalLeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalLeaveServiceImpl implements PersonalLeaveService {

    @Autowired
    private PersonalLeaveDao personalLeaveDao;

    @Override
    public PageInfo<PersonalLeavePo> page(Integer page, Integer size, String departmentName, Integer personalId, Integer year, Integer month) {

        PageHelper.startPage(page,size);
        PageInfo<PersonalLeavePo> pageInfo = new PageInfo<>(personalLeaveDao.selectByCondition(departmentName, personalId, year, month));
        return pageInfo;
    }

    @Override
    public List<PersonalLeavePo> selectAll() {
        return personalLeaveDao.selectAll();
    }

    @Override
    public int save(PersonalLeavePo personalLeavePo) {
        return personalLeaveDao.save(personalLeavePo);
    }

    @Override
    public PersonalLeavePo selectById(Long id) {
        return personalLeaveDao.selectById(id);
    }

    @Override
    public int update(PersonalLeavePo personalLeavePo) {
        return personalLeaveDao.update(personalLeavePo);
    }
}
