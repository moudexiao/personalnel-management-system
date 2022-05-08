package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.PersonalAttendancePo;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.vo.input.AttendanceInOrOutInput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalAttendanceService {

    //分页查询
    PageInfo<PersonalAttendancePo> page(Integer page, Integer size, String departmentName,
                                        Integer personalId, Integer year, Integer month);

    /**
     * 查询全部
     * @return
     */
    List<PersonalAttendancePo> selectAll();

    /**
     * 添加
     * @param personalAttendancePo
     * @return
     */
    int save(PersonalAttendancePo personalAttendancePo);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    PersonalAttendancePo selectById(Long id);

    /**
     * 更新数据
     * @param personalAttendancePo
     * @return
     */
    int update(PersonalAttendancePo personalAttendancePo);

    /**
     * 删除
     * @param ids
     * @return
     */
    int batchDelete(List<Long> ids);

    void inOrOut(AttendanceInOrOutInput input, UserPo user);
}
