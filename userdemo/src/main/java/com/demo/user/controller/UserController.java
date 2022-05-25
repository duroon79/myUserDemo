/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.controller;

import com.demo.user.dao.UserRepository;
import com.demo.user.dto.UserDto;
import com.demo.user.entity.User;
import com.demo.user.exception.ServiceException;
import com.demo.user.service.IUserService;

import com.demo.user.so.ErrorEnum;
import com.demo.user.so.UnionResponse;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserController
 * @Description 用于用户相关操作的控制类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
@RestController
public class UserController extends BaseController{

    private static final String REGISTER_USER_SUCCESS = "用户注册成功";
    private static final String UPDATE_USER_SUCCESS = "用户更新成功";
    private static final String DELETE_USER_SUCCESS = "用户删除成功";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userServiceImpl;

    /**
     * 注册用户
     * @param userDto 用户传输对象
     * @return UnionResponse 返回信息
     */
    @PostMapping("/registerUser")
    public UnionResponse registerUser(@RequestBody UserDto userDto){
        try{
            validateUser(userDto);
            User user = convertUserDTO(userDto);
            //密码字段需要加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userServiceImpl.registUser(user);
        }
        catch (ServiceException e){
            return error(e.getMessage());
        }
        return success(REGISTER_USER_SUCCESS);
    }

    /**
     * 更新一个或多个用户
     * @param userDtoList 待更新用户列表
     * @return 更新结果
     */
    @PostMapping("/updateUser")
    public UnionResponse updateUser(@RequestBody List<UserDto> userDtoList){
        List<User> userList = new ArrayList<User>();
        userDtoList.forEach((e) -> {
            validateUser(e);
            userList.add(convertUserDTO(e));
        });
        userServiceImpl.updateUser(userList);
        return success(UPDATE_USER_SUCCESS);
    }

    /**
     * 删除一个或多个用户
     * @param userDtoList 待删除用户列表
     * @return
     */
    @PostMapping("/deleteUser")
    public UnionResponse deleteUser(@RequestBody List<UserDto> userDtoList){
        List<User> userList = new ArrayList<User>();
        userDtoList.forEach((e) -> {
            userList.add(convertUserDTO(e));
        });
        userServiceImpl.deelteUser(userList);
        return success(DELETE_USER_SUCCESS);

    }

    /**
     * 查找指定ID的所有用户
     * @param userDtoList 用户id集合
     * @return 用户信息
     */
    @PostMapping("/findAllById")
    public List<UserDto> findAllUser(@RequestBody List<UserDto> userDtoList){
        List<Long> ids = new ArrayList<>();
        userDtoList.forEach((e)->{
            ids.add(e.getId());
        });
        List<User> userList = userServiceImpl.findAllById(ids);
        List<UserDto> userDtos = new ArrayList<>();
        userList.forEach((e)->{
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(e,userDto);
            userDtos.add(userDto);
        });
        return userDtos;
    }
    /**
     * 用户转换方法
     * @param userDto 用户传输对象
     * @return user 用户实体对象
     */
    private User convertUserDTO(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }

    /**
     * 用户信息校验
     * @param userDto 用户传输对象
     */
    private void validateUser(UserDto userDto){
        if(StringUtils.isEmpty(userDto.getUserName())){
            throw new ServiceException(ErrorEnum.USER_NAME_IS_NOT_NULL);
        }
        if(StringUtils.isEmpty(userDto.getPassword())){
            throw new ServiceException(ErrorEnum.PWD_IS_NOT_NULL);
        }
        if(StringUtils.isEmpty(userDto.getEmail())){
            throw new ServiceException(ErrorEnum.EMAIL_IS_NOT_NULL);
        }
        if(userDto.getAge() == null){
            throw new ServiceException(ErrorEnum.AGE_IS_NOT_NULL);
        }
        //检查收件人地址格式是否正确
        if(userDto.getEmail().indexOf("@")==-1){
            throw new ServiceException(ErrorEnum.EMAIL_FORMAT_NOT_CORRECT);
        }
    }
}
