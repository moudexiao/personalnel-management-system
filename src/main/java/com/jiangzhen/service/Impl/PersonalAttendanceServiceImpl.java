package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalAttendanceDao;
import com.jiangzhen.po.PersonalAttendancePo;
import com.jiangzhen.service.PersonalAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalAttendanceServiceImpl implements PersonalAttendanceService {

    @Autowired
    private PersonalAttendanceDao personalAttendanceDao;

    @Override
    public PageInfo<PersonalAttendancePo> page(Integer page, Integer size, String departmentName, Integer personalId, Integer year, Integer month) {

        PageHelper.startPage(page,size);
        PageInfo<PersonalAttendancePo> pageInfo = new PageInfo<>(personalAttendanceDao.selectByCondition(departmentName, personalId, year, month));
        return pageInfo;
    }

    @Override
    public List<PersonalAttendancePo> selectAll() {
        return personalAttendanceDao.selectAll();
    }

    @Override
    public int save(PersonalAttendancePo personalAttendancePo) {
        return personalAttendanceDao.save(personalAttendancePo);
    }

    @Override
    public PersonalAttendancePo selectById(Long id) {
        return personalAttendanceDao.selectById(id);
    }

    @Override
    public int update(PersonalAttendancePo personalAttendancePo) {
        return personalAttendanceDao.update(personalAttendancePo);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return personalAttendanceDao.batchDelete(ids);
    }
}
