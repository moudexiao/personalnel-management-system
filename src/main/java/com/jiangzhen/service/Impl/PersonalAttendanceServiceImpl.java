package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.PersonalAttendanceDao;
import com.jiangzhen.po.DepartmentPo;
import com.jiangzhen.po.PersonalAttendancePo;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.DepartmentService;
import com.jiangzhen.service.PersonalAttendanceService;
import com.jiangzhen.service.PersonalService;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PersonalVo;
import com.jiangzhen.vo.PositionVo;
import com.jiangzhen.vo.input.AttendanceInOrOutInput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonalAttendanceServiceImpl implements PersonalAttendanceService {

    @Autowired
    private PersonalAttendanceDao personalAttendanceDao;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;

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

    @Override
    public void inOrOut(AttendanceInOrOutInput input, UserPo user) {
        PersonalVo personalVo = personalService.findById(user.getPersonalId());
        PersonalAttendancePo personalAttendancePo = personalAttendanceDao.findByPersonalIdAndAttendanceTime(personalVo.getId(), input.getAttendanceTime());
        if (personalAttendancePo != null) {
            //下班卡
            personalAttendancePo.setClockOut(input.getClockOut());
            update(personalAttendancePo);
        } else {
            //上班卡
            DepartmentPo departmentPo = departmentService.selectById(personalVo.getDepartmentId());

            PositionVo positionVo = positionService.selectById(personalVo.getPositionId());

            personalAttendancePo = new PersonalAttendancePo();
            personalAttendancePo.setAttendanceTime(input.getAttendanceTime());
            personalAttendancePo.setPunchIn(input.getPunchIn());
            personalAttendancePo.setPersonalId(personalVo.getId());
            personalAttendancePo.setPersonalName(personalVo.getPersonalName());
            personalAttendancePo.setDepartmentName(departmentPo.getDepartmentName());
            personalAttendancePo.setPositionName(positionVo.getPositionName());
            save(personalAttendancePo);
        }
    }
}
