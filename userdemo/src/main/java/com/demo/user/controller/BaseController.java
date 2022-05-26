/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.controller;

import com.demo.user.so.Result;
import com.demo.user.so.ResultCode;

/**
 * @ClassName BaseController
 * @Description 基础控制类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
public class BaseController<T> {
    /**
     * 返回成功应答
     *
     * @return Result 处理结果
     */
    protected Result success() {
        return Result.SUCCESS();

    }

    /**
     * 返回系统错误
     * @return 处理结果
     */
    protected Result error() {
        return Result.FAIL();
    }

    /**
     * 返回业务相关错误
     * @param resultCode 处理结果代码
     * @return 处理结果
     */
    protected Result error(ResultCode resultCode) {
        return Result.ERROR(resultCode);
    }

    protected Result<T> data(ResultCode resultCode,T data){
        Result<T> result = new Result(resultCode,data);
        return result;
    }
}
