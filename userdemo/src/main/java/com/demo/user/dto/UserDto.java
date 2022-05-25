/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserDto
 * @Description 用户数据传输类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
@Setter
@Getter
public class UserDto {
    private Long id;

    private String userName;

    private String password;

    private String name;

    private Integer age;

    private String email;

    private String isDeleted;
}
