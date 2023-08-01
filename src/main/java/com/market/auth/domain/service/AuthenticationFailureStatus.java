package com.market.auth.domain.service;

import com.market.auth.exception.InvalidPasswordException;
import com.market.auth.exception.InvalidUsernameException;
import com.market.auth.exception.UnauthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum AuthenticationFailureStatus {
    DEFAULT(0, UnauthenticationException.class),
    NO_EXISTS_MEMBER(1, UsernameNotFoundException.class),
    INVALID_USERNAME(2, InvalidUsernameException.class),
    INVALID_PASSWORD(3, InvalidPasswordException.class)
    ;

    private final int code;
    private final Class<? extends AuthenticationException> cause;

    private static final Map<Class<? extends AuthenticationException>, AuthenticationFailureStatus> causeMap = Stream
                    .of(AuthenticationFailureStatus.values())
                    .collect(Collectors.toMap(
                            AuthenticationFailureStatus::cause,
                            status -> status,
                            (oldValue, newValue) -> oldValue
                    ));

    AuthenticationFailureStatus(int code, Class<? extends AuthenticationException> cause) {
        this.code = code;
        this.cause = cause;
    }

    public int code() {
        return code;
    }

    public static int code(AuthenticationException exception) {
        return get(exception).code();
    }

    public Class<? extends AuthenticationException> cause() {
        return cause;
    }

    public static AuthenticationFailureStatus get(AuthenticationException exception) {
        return causeMap.getOrDefault(exception.getClass(), AuthenticationFailureStatus.DEFAULT);
    }

}