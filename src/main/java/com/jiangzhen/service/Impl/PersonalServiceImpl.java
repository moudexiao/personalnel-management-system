package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalDao;
import com.jiangzhen.enums.ResultEnum;
import com.jiangzhen.exception.BaseException;
import com.jiangzhen.po.PersonalPo;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.vo.PersonalVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    public int update(PersonalVo personal) {
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
    public List<PersonalVo> findAll(){
        return personalDao.findAll();
    }


    @Override
    public PersonalVo findById(Long id) {
        PersonalVo byId = personalDao.findById(id);
        if(ObjectUtils.isEmpty(byId))
        {
            throw new BaseException(ResultEnum.PERSONAL_SALARY_NOT_EXIST);
        }
        return byId;
    }

    @Override
    public PageInfo<PersonalVo> page(Integer page, Integer size, Long departmentId, String personalName, Integer workStatus) {
        PageHelper.startPage(page,size);
        PageInfo<PersonalVo> pageInfo = new PageInfo<>(personalDao.queryAll(departmentId, personalName, workStatus));
        return pageInfo;
    }

}
