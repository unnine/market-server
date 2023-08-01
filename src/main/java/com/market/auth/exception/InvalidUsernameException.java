package com.market.auth.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class InvalidUsernameException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 562360153412400645L;

    public InvalidUsernameException(String msg) {
        super(msg);
    }

}