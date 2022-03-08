package com.jiangzhen.service.Impl;

import com.jiangzhen.dao.PersonalDao;
import com.jiangzhen.po.PersonalPo;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.vo.PersonalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalDao personalDao;
    @Override
    public int save(PersonalPo personal) {
        personal.setCreateTime(new Date());
        return personalDao.save(personal);
    }

    @Override
    public int update(PersonalPo personal) {
        personal.setUpdateTime(new Date());
        return personalDao.update(personal);
    }

    @Override
    public int deleteById(Long id) {
        return personalDao.deleteById(id);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return personalDao.batchDelete(ids);
    }

    @Override
    public int selectByName(String pname) {
        return personalDao.selectByName(pname);
    }

    @Override
    public int selectByStatus(String workStatus) {
        return personalDao.selectByStatus(workStatus);
    }

    @Override
    public List<PersonalVo> findAll() {
        return personalDao.findAll();
    }
}
