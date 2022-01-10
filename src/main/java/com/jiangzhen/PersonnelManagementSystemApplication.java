package com.jiangzhen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.jiangzhen.dao")//Mybatis的DAO所在包
public class PersonnelManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonnelManagementSystemApplication.class, args);
    }

}
