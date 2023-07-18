package com.market.common.config;

import com.market.auth.config.SecurityAuthenticationConfigurer;
import com.market.auth.config.SecurityAuthorizationConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityAuthenticationConfigurer authenticationConfigurer;
    private final SecurityAuthorizationConfigurer authorizationConfigurer;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain authenticationFilterChain(HttpSecurity http) throws Exception {
        authenticationConfigurer.config(http);
        authorizationConfigurer.config(http);
        return http.build();
    }

}