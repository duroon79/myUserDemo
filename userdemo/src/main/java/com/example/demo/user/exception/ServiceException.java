/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.example.demo.user.exception;

import com.example.demo.user.so.ResultCode;
import lombok.Getter;

/**
 * @ClassName ServiceException
 * @Description 统一异常类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
@Getter
public class ServiceException extends RuntimeException {

    /**
     * 自定义异常枚举类
     */
    private ResultCode resultCode;
    //异常代码
    private int code;
    //异常消息
    private String errorMsg;
    public ServiceException() {
        super();
    }

    public ServiceException(ResultCode resultCode) {
        super("{errorCode:" + resultCode.code() + ",errorMsg:" + resultCode.message() + "}");
        this.resultCode = resultCode;
        this.code = resultCode.code();
        this.errorMsg = resultCode.message();
    }
}
