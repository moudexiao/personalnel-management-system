package com.jiangzhen.dao;

import com.jiangzhen.po.UserPo;
import com.jiangzhen.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int save(UserPo user);

    int update(UserPo user);

    int deleteById(Long id);

    int batchDelete(List<Long> ids);

    UserPo findByUsername(String username);

    UserPo findById(Long id);


    List<UserVo> findAll();
}
