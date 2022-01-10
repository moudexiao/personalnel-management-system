package com.jiangzhen.service.Impl;

import com.jiangzhen.dao.UserDao;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhaoyiming
 * @date: 2022-01-07 12:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void addUser(UserPo userPo) {
        userDao.addUser(userPo);
    }
}
