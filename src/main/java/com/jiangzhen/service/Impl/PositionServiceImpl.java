package com.jiangzhen.service.Impl;

import com.jiangzhen.dao.PositionDao;
import com.jiangzhen.po.PositionPo;
import com.jiangzhen.service.PositionService;
import com.jiangzhen.vo.PositionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionDao positionDao;
    @Override
    public int save(PositionPo position) {
        return positionDao.save(position);
    }

    @Override
    public int update(PositionPo position) {
        return positionDao.update(position);
    }

    @Override
    public int deletePositionById(Long id) {
        return positionDao.deletePositionById(id);
    }

    @Override
    public int batchDeletePosition(List<Long> ids) {
        return positionDao.batchDeletePosition(ids);
    }

    @Override
    public PositionVo selectById(Long id) {
        return positionDao.selectById(id);
    }

    @Override
    public PositionVo selectByPositionName(String positionName) {
        return positionDao.selectByPositionName(positionName);
    }

    @Override
    public List<PositionVo> selectAll(Long departmentId) {
        return positionDao.selectAll(departmentId);
    }
}
