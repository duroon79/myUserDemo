/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.example.demo.user.service;

import com.example.demo.user.entity.User;
import com.example.demo.user.dao.UserRepository;
import com.example.demo.user.exception.ServiceException;
import com.example.demo.user.so.ResultCode;
import com.example.demo.user.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
            user.setCreateDate(new Date());
            user.setUpdatedDate(new Date());
            userRepository.save(user);
            //发送注册成功邮件
            emailUtil.sendEmail(mailFrom,user.getEmail(),subject,mailContent);
        }
        else{

            throw new ServiceException(ResultCode.USER_EXIST);
        }
    }

    /**
     * 更新一个或多个用户
     * @param userList 待更新用户列表
     */
    @Transactional
    public void updateUser(List<User> userList){
        userList.forEach((e)->{
            userRepository.updateUser(e.getUserName(),e.getEmail(),e.getName(),e.getAge(),new Date(),e.getId());
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
     * 查找一个用户
     * @param ids 用户id
     * @return 用户信息
     */
    public List<User> findByIds (List<Long> ids){
       return userRepository.findAllById(ids);
    }

    /**
     * 查找所有用户
     * @return 所有用户信息
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

}
