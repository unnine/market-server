package com.market.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityAuthorizationConfigurer implements SecurityConfigurer {

    @Override
    public void config(HttpSecurity http) throws Exception {
        permitRequests(http);
    }

    private void permitRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> registry.anyRequest().permitAll());
    }

}