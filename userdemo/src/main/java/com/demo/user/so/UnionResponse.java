/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.so;



/**
 * @ClassName UnionResponse
 * @Description 对返回前端的数据进行封装
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/

public class UnionResponse {
    private String code;
    private String message;

    public UnionResponse setCode(ResponseCode code) {
        this.code = code.getCode();
        return this;
    }
    public UnionResponse setCode(String code) {
        this.code = code;
        return this;
    }
    public UnionResponse setMessage(String  message) {
        this.message =message;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
