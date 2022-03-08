package com.jiangzhen.service.Impl;

import com.jiangzhen.dao.RoleDao;
import com.jiangzhen.service.RoleService;
import com.jiangzhen.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleVo> findAll() {
        return roleDao.findAll();
    }
}
