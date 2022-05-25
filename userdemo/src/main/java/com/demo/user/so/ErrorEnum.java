/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.so;

/**
 * @ClassName ErrorEnum
 * @Description 自定义异常枚举类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
public enum ErrorEnum {
    //自定义系列
    USER_NAME_IS_NOT_NULL("10001","【参数校验】用户名不能为空"),
    PWD_IS_NOT_NULL("10002","【参数校验】密码不能为空"),
    EMAIL_IS_NOT_NULL("10003","【参数校验】收件地址不能为空"),
    USER_EXIST("10004","当前用户已经注册"),
    EMAIL_FORMAT_NOT_CORRECT("10005","【参数校验】邮件地址格式不正确"),
    AGE_IS_NOT_NULL("10006","【参数校验】年龄不能为空");


    /** 错误码 */
    private String code;

    /** 错误描述 */
    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}