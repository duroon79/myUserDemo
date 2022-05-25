/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.service;

import com.demo.user.dao.UserRepository;
import com.demo.user.entity.User;
import com.demo.user.exception.ServiceException;
import com.demo.user.so.ErrorEnum;
import com.demo.user.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UserService
 * @Description 用户服务类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailUtil emailUtil;
    @Value("${spring.mail.username}")
    private String mailFrom;
    @Value("${user.mail.subject}")
    private String subject;
    @Value("${user.mail.content}")
    private String mailContent;


    /**
     * 用户注册方法，需要检查用户是否存在
     * @param user 用户传输对象
     */
    public void registUser(User user){
        //先检查注册用户是否存在，如果存在需要抛出异常
        User userDB = this.userRepository.findByUserName(user.getUserName());
        if(userDB == null){
            userRepository.save(user);
            //发送注册成功邮件
            emailUtil.sendEmail(mailFrom,user.getEmail(),subject,mailContent);
        }
        else{

            throw new ServiceException(ErrorEnum.USER_EXIST);
        }
    }

    /**
     * 更新一个或多个用户
     * @param userList 待更新用户列表
     */
    @Transactional
    public void updateUser(List<User> userList){
        userList.forEach((e)->{
            userRepository.updateUser(e.getUserName(),e.getEmail(),e.getName(),e.getAge(),e.getId());
        });
    }


    /**
     * 删除一个或多个用户
     * @param userList 待删除用户列表
     */
    @Transactional
    public void deelteUser(List<User> userList){
        userList.forEach((e)->{
            userRepository.deleteUser(e.getId());
        });
    }

    /**
     * 查找所有用户
     * @return 返回所有用户信息
     */
    public List<User> findAllById(List<Long> ids){
        return userRepository.findAllById(ids);
    }

}
