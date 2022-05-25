/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.exception;

import com.demo.user.so.ErrorEnum;
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
    private ErrorEnum errorEnum;
    //异常代码
    private String code;
    //异常消息
    private String errorMsg;
    public ServiceException() {
        super();
    }

    public ServiceException(ErrorEnum errorEnum) {
        super("{errorCode:" + errorEnum.getCode() + ",errorMsg:" + errorEnum.getMsg() + "}");
        this.errorEnum = errorEnum;
        this.code = errorEnum.getCode();
        this.errorMsg = errorEnum.getMsg();
    }
}
