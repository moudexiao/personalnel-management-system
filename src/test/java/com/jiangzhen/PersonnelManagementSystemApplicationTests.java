package com.jiangzhen;

import com.jiangzhen.common.utils.JWTUtils;
import com.jiangzhen.po.UserPo;
import com.jiangzhen.service.UserService;
import com.jiangzhen.vo.input.UserInput;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonnelManagementSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserService userService;

    @Test
    void productUser(){
        UserInput input = new UserInput();
        input.setUsername("zhao");
        input.setPassword("zhao");
        input.setRoleId(3);
        String salt = JWTUtils.getSalt();
        String password = new Md5Hash(input.getPassword(), salt, 100).toHex();
        UserPo user = new UserPo();
        BeanUtils.copyProperties(input, user);
        user.setSalt(salt);
        user.setPassword(password);
//        user.setId(new Long("2"));
//        userService.update(user);
        userService.save(user);
    }

}
