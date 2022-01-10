package com.jiangzhen.dao;

import com.jiangzhen.po.UserPo;
import org.springframework.stereotype.Repository;

/**
 * @author: zhaoyiming
 * @date: 2022-01-07 11:41
 */
@Repository
public interface UserDao {

    void addUser(UserPo user);
}
