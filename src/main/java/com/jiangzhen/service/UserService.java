package com.jiangzhen.service;

import com.jiangzhen.po.UserPo;

/**
 * @author: zhaoyiming
 * @date: 2022-01-07 11:59
 */
public interface UserService {
    void addUser(UserPo userPo);

    /**
     * 根据username获取用户
     * @param username
     * @return
     */
    UserPo findUserByName(String username);
}
