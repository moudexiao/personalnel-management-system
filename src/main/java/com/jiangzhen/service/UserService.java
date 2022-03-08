package com.jiangzhen.service;

import com.github.pagehelper.PageInfo;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.vo.UserVo;

import java.util.List;

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

    /**
     * 分页
     * @param page
     * @param size
     * @return
     */
    PageInfo<UserVo> page(Integer page, Integer size);

    /**
     * 根据id删除数据
     * @param id
     */
    int deleteById(Long id);


    /**
     * 根据id批量删除
     * @param ids
     * @return
     */

    int batchDelete(List<Long> ids);

    UserPo findById(Long id);

    List<UserVo> findAll();
}
