package com.jiangzhen.service;

import com.jiangzhen.po.UserPo;

public interface UserService {

    /**
     * 根据username获取用户
     * @param username
     * @return
     */
    UserPo findByUsername(String username);

    /**
     * 保存
     * @param user
     * @return
     */
    int save(UserPo user);

    /**
     * 更新
     * @param user
     * @return
     */
    int update(UserPo user);
}
