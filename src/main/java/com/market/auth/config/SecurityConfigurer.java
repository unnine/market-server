package com.market.auth.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityConfigurer {

    void config(HttpSecurity http) throws Exception;

}