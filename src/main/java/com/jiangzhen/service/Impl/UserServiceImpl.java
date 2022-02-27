package com.jiangzhen.service.Impl;

import com.jiangzhen.dao.UserDao;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserPo findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public int save(UserPo user) {
        return userDao.save(user);
    }

    @Override
    public int update(UserPo user) {
        return userDao.update(user);
    }
}
