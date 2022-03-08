package com.jiangzhen.service.Impl;

import com.jiangzhen.dao.DepartmentDao;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.vo.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;


    @Override
    public List<DepartmentVo> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public int selectByDepartmentName(String departmentName) {
        return departmentDao.selectByDepartmentName(departmentName);
    }

    @Override
    public int save(DepartmentPo department) {
            department.setCreateTime(new Date());
        return departmentDao.save(department);
    }

    @Override
    public int update(DepartmentPo department) {
        department.setUpdateTime(new Date());
        return departmentDao.update(department);
    }

    @Override
    public int deleteById(Long id) {
        return departmentDao.deleteById(id);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return departmentDao.batchDelete(ids);
    }
}
