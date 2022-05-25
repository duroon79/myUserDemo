/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.controller;

import com.demo.user.so.ResponseCode;
import com.demo.user.so.UnionResponse;

/**
 * @ClassName BaseController
 * @Description 基础控制类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
public class BaseController {
    /**
     * 返回成功应答
     * @param message  需要返回的消息
     * @return UnionResponse
     */
    protected UnionResponse success(String message) {
        return new UnionResponse()
                .setCode(ResponseCode.Success)
                .setMessage(message);

    }

    /**
     * 返回失败应答
     * @param message 需要返回的消息
     * @return
     */
    protected UnionResponse error(String message) {
        return new UnionResponse()
                .setCode(ResponseCode.Failed)
                .setMessage(message);
    }

    /**
     * 返回包含错误代码的失败应答
     * @param exceptionCode 异常代码
     * @param message 消息
     * @return
     */
    protected UnionResponse error(String exceptionCode,String message) {
        return new UnionResponse()
                .setCode(exceptionCode)
                .setMessage(message);
    }
}
