/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.so;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Result
 * @Description 返回结果封装类
 * @Author SQLUO
 * @Date 2022/5/22
 * Version 1.0
 **/
@Data
@NoArgsConstructor
public class Result<T> {

    private boolean success;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息
    private T data;// 返回数据

    public Result(ResultCode code) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code,T data) {
        this.success = code.success;
        this.code = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code,String message,boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    /**
     * 返回处理成功的结果
     * @return 执行结果
     */
    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 返回业务校验失败的结果
     * @param code 结果代码
     * @return 执行结果
     */
    public static Result ERROR(ResultCode code){
        return new Result(code);
    }

    /**
     * 返回处理失败的结果
     * @return 执行结果
     */
    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }



}