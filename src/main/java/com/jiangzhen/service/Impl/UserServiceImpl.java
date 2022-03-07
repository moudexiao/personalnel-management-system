package com.jiangzhen.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiangzhen.dao.RoleDao;
import com.jiangzhen.dao.UserDao;
import com.jiangzhen.po.RolePo;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import com.jiangzhen.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserPo findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int save(UserPo user) {
        user.setCreateTime(new Date());
        return userDao.save(user);
    }

    @Override
    public int update(UserPo user) {
        user.setUpdateTime(new Date());
        return userDao.update(user);
    }

    @Override
    public PageInfo<UserVo> page(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        PageInfo<UserVo> pageInfo = new PageInfo<>(userDao.findAll());
        return pageInfo;
    }

    @Override
    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<RolePo> roleList() {
        return roleDao.findAll();
    }
}
