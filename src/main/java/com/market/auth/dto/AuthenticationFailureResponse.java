package com.market.auth.dto;

import com.market.auth.domain.service.AuthenticationFailureStatus;
import com.market.common.dto.BaseResponse;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailureResponse extends BaseResponse {

    public AuthenticationFailureResponse(AuthenticationException exception) {
        super(AuthenticationFailureStatus.code(exception), exception.getMessage());
    }
}