package com.market.common.dto;

import lombok.Getter;

@Getter
public class BaseResponse {

    private final int statusCode;
    private final String message;

    public BaseResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}