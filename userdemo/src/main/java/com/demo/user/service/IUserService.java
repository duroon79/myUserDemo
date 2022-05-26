/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.service;

import com.demo.user.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @InterfaceName IUserService
 * @Description TODO
 * @Author SQLUO
 * @Date 2022/5/22
 * Version 1.0
 **/
public interface IUserService {
    /**
     * 用户注册方法，需要检查用户是否存在
     * @param user 用户传输对象
     */
    public void registUser(User user);


    /**
     * 更新一个或多个用户
     * @param userList 待更新用户列表
     */
    public void updateUser(List<User> userList);

    /**
     * 删除一个或多个用户
     * @param userList 待删除用户列表
     */
    public void deelteUser(List<User> userList);



    /**
     * 查找一个用户
     * @param ids 用户id
     * @return 用户信息
     */
    public List<User> findByIds(List<Long> ids);

    /**
     * 查找所有用户
     * @return
     */
    public List<User> findAll();

}
