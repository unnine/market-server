package com.market.auth.exception;

import org.springframework.security.core.AuthenticationException;

import java.io.Serial;

public class UnauthenticationException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = -5499842766809186629L;

    public UnauthenticationException() {
        super("Invalid authenticate attempt");
    }
}