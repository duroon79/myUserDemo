/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.service;

import com.demo.user.entity.User;
import com.demo.user.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserServiceTest
 * @Description 单元测试类
 * @Author SQLUO
 * @Date 2022/5/25
 * Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    IUserService userService;
    @Test
    public void testRegistUser(){
        User user = new User();
        user.setUserName("testUser2");
        user.setPassword("111111");
        user.setAge(10);
        user.setEmail("duroon@qq.com");
        userService.registUser(user);
    }
    @Test(expected = ServiceException.class)
    public void testRegistUserWithDuplicateUser(){
        User user = new User();
        user.setUserName("testUser");
        user.setPassword("111111");
        user.setAge(10);
        user.setEmail("duroon@qq.com");
        userService.registUser(user);
        userService.registUser(user);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(1L);
        user.setUserName("testUser");
        user.setPassword("111111");
        user.setAge(10);
        user.setEmail("duroon@qq.com");
        List userList = new ArrayList();
        userList.add(user);
        userService.updateUser(userList);
    }

    @Test
    public void testDeleteUser(){
        User user = new User();
        user.setId(1L);
        List userList = new ArrayList();
        userList.add(user);
        userService.deelteUser(userList);
    }

    @Test
    public void testFindUserByIds(){
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        userService.findByIds(ids);
    }

    @Test
    public void testFindAllUser(){

        userService.findAll();
    }
}
