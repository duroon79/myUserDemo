/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.so;

/**
 * @ClassName ResponseCode
 * @Description 返回类
 * @Author SQLUO
 * @Date 2022/5/21
 * Version 1.0
 **/
public enum ResponseCode {
    Success("20000"), Failed("50000");
    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
