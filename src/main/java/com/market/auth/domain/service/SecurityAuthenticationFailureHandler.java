package com.market.auth.domain.service;

import com.fasterxml.jackson.core.JsonEncoding;
import com.market.auth.dto.AuthenticationFailureResponse;
import com.market.common.dto.BaseResponse;
import com.market.common.util.JsonEncoder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error(exception.getMessage(), exception);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(JsonEncoding.UTF8.getJavaName());

        AuthenticationFailureResponse responseDto = new AuthenticationFailureResponse(exception);
        String body = JsonEncoder.encode(responseDto);
        response.getWriter().write(body);
    }
}