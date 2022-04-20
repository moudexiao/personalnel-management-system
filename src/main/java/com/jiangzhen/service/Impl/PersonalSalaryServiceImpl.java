package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalSalaryDao;
import com.jiangzhen.po.PersonalSalary;
import com.jiangzhen.service.PersonalSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalSalaryServiceImpl implements PersonalSalaryService {

    @Autowired
    private PersonalSalaryDao personalSalaryDao;

//    @Override
//    public List<PersonalSalary> selectAll() {
//        return personalSalaryDao.selectAll();
//    }

    @Override
    public PageInfo<PersonalSalary> page(Integer page, Integer size, String departmentName, Integer personalId, Integer year, Integer month) {
        PageHelper.startPage(page,size);
        PageInfo<PersonalSalary> pageInfo = new PageInfo<>(personalSalaryDao.selectByCondition(departmentName,personalId,year,month));
        return pageInfo;
    }

    @Override
    public int save(PersonalSalary personalSalary) {
        return personalSalaryDao.save(personalSalary);
    }

    @Override
    public int update(PersonalSalary personalSalary) {
        return personalSalaryDao.update(personalSalary);
    }

    @Override
    public PersonalSalary selectById(Long id) {
        return personalSalaryDao.selectById(id);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return personalSalaryDao.batchDelete(ids);
    }

    @Override
    public PersonalSalary selectAll() {
        return personalSalaryDao.selectAll();
    }


}
