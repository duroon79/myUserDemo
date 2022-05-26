/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.controller;

import com.demo.user.dao.UserRepository;
import com.demo.user.dto.UserDto;
import com.demo.user.entity.User;
import com.demo.user.exception.ServiceException;
import com.demo.user.service.IUserService;


import com.demo.user.so.Result;
import com.demo.user.so.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.assertj.core.util.Arrays;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName UserController
 * @Description 用于用户相关操作的控制类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
@RestController
@Api(tags = "用户管理接口")
public class UserController<T> extends BaseController{


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userServiceImpl;

    /**
     * 注册用户
     * @param userDto 用户传输对象
     * @return UnionResponse 返回信息
     */
    @PostMapping("/user")
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public Result registerUser(@RequestBody UserDto userDto){
        try{
            validateUser(userDto);
            User user = convertUserDTO(userDto);
            //密码字段需要加密
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userServiceImpl.registUser(user);
        }
        catch (ServiceException e){
            return error(e.getResultCode());
        }
        return success();
    }

    /**
     * 更新一个或多个用户
     * @param userDtoList 待更新用户列表
     * @return 更新结果
     */
    @PutMapping("/users")
    @ApiOperation(value = "更新用户信息", notes = "更新一个或多个用户信息")
    public Result updateUser(@RequestBody List<UserDto> userDtoList){
        List<User> userList = new ArrayList<User>();
        userDtoList.forEach((e) -> {
            validateUser(e);
            userList.add(convertUserDTO(e));
        });
        userServiceImpl.updateUser(userList);
        return success();
    }

    /**
     * 删除一个或多个用户
     * @param userDtoList 待删除用户列表
     * @return
     */

    @DeleteMapping("/users")
    @ApiOperation(value = "逻辑删除用户", notes = "根据用户id删除一个或多个用户")
    public Result deleteUser(@RequestBody List<UserDto> userDtoList){
        List<User> userList = new ArrayList<User>();
        userDtoList.forEach((e) -> {
            userList.add(convertUserDTO(e));
        });
        userServiceImpl.deelteUser(userList);
        return success();

    }


    /**
     * 查找所有用户信息
     * @return 用户信息
     */
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/users")
    public Result<T> findAllUser(){

        List<User> userList = userServiceImpl.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        userList.forEach((e)->{
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(e,userDto);
            userDtos.add(userDto);
        });
        return data(ResultCode.SUCCESS,userDtos);
    }
    @ApiOperation(value = "查询用户", notes = "根据用户id查询用户")
    @ApiImplicitParam(paramType = "path", name = "ids", value = "用户id", dataType = "String", example = "1,2",
            required = true)
    @GetMapping("/users/{ids}")
    public Result<User> findById(@PathVariable(value="ids") String id) {

         List<Object> ids = Arrays.asList(id.split(","));
        List<Long> idList = new ArrayList<>();
        ids.forEach((e)->{
            idList.add(new Long((String) e));
        });
        List<User> users = userServiceImpl.findByIds(idList);
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach((e)->{
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(e,userDto);
            userDtos.add(userDto);
        });
        return data(ResultCode.SUCCESS,userDtos);
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
            throw new ServiceException(ResultCode.USER_NAME_IS_NOT_NULL);
        }
        if(StringUtils.isEmpty(userDto.getPassword())){
            throw new ServiceException(ResultCode.PWD_IS_NOT_NULL);
        }
        if(StringUtils.isEmpty(userDto.getEmail())){
            throw new ServiceException(ResultCode.EMAIL_IS_NOT_NULL);
        }
        if(userDto.getAge() == null){
            throw new ServiceException(ResultCode.AGE_IS_NOT_NULL);
        }
        //检查收件人地址格式是否正确
        if(userDto.getEmail().indexOf("@")==-1){
            throw new ServiceException(ResultCode.EMAIL_FORMAT_NOT_CORRECT);
        }
    }
}
