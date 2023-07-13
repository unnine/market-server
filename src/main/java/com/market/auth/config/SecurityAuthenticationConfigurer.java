package com.market.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationConfigurer implements SecurityConfigurer {

    private final String loginUrl = "/login";
    private final String logoutUrl = "/logout";
    private final String sessionIdCookieName = "JSESSIONID";

    @Override
    public void config(HttpSecurity http) throws Exception {
        disableConfig(http);
        logoutConfig(http);
        sessionConfig(http);
    }

    private void disableConfig(HttpSecurity http) throws Exception {
        http
                .formLogin(FormLoginConfigurer::disable)
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .rememberMe(RememberMeConfigurer::disable)
                .cors(CorsConfigurer::disable);
    }

    private void logoutConfig(HttpSecurity http) throws Exception {
        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl(loginUrl)
                .deleteCookies(sessionIdCookieName)
        );
    }

    private void sessionConfig(HttpSecurity http) throws Exception {
        http.sessionManagement(configurer -> configurer
                .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId)
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl(loginUrl)
                .maximumSessions(-1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl(loginUrl)
        );
    }

}