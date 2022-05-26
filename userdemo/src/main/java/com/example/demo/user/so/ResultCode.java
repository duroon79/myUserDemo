/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.example.demo.user.so;

/**
 * 公共的返回码
 *    返回码code：
 *      成功：10000
 *      失败：10001
 *      用户名不能为空:10002
 *      密码不能为空 10003
 *      收件邮箱不能为空 10004
 *      当前用户已经注册 10005
 *      电子邮件地址格式不正确 10006
 *      年龄不能为空 10007
 */
public enum ResultCode {
    SUCCESS(true,10000,"操作成功！"),
    //---系统错误返回码-----
    FAIL(false,10001,"操作失败"),
    //自定义系列
    USER_NAME_IS_NOT_NULL(false,10002,"【参数校验】用户名不能为空"),
    PWD_IS_NOT_NULL(false,10003,"【参数校验】密码不能为空"),
    EMAIL_IS_NOT_NULL(false,410004,"【参数校验】收件邮箱不能为空"),
    USER_EXIST(false,10005,"当前用户已经注册"),
    EMAIL_FORMAT_NOT_CORRECT(false,10006,"【参数校验】电子邮件地址格式不正确"),
    AGE_IS_NOT_NULL(false,10007,"【参数校验】年龄不能为空");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
