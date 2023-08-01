package com.market.auth.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class InvalidPasswordException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = -4191269823384561161L;

    public InvalidPasswordException(String msg) {
        super(msg);
    }
}