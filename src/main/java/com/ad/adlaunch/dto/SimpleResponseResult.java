package com.ad.adlaunch.dto;

import lombok.Data;

import java.util.Map;

/**
 * @author : wezhyn
 * @date : 2019/09/20
 * <p>
 * Copyright (c) 2018-2019 All Rights Reserved.
 */
@Data
public class SimpleResponseResult<T> {

    /**
     * 成功：20000
     */
    private int code;
    public static final int SUCCESS_CODE=20000;
    public static final int FAILURE_CODE=50000;

    private T data;
    private String message;

    public SimpleResponseResult(int code, T data, String message) {
        this.code=code;
        this.data=data;
        this.message=message;
    }

    public  static <T> SimpleResponseResult<T> successResponseResult(String message, T data) {
        return new SimpleResponseResult<>(SUCCESS_CODE, data, message);
    }

}
