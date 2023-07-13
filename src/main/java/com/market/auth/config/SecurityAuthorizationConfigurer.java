package com.market.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityAuthorizationConfigurer implements SecurityConfigurer {

    @Override
    public void config(HttpSecurity http) throws Exception {
        permitAllRequests(http);
    }

    /*
            인가 처리는 AOP를 이용해 각 컨트롤러 메서드 레벨에서 처리하므로 Security 설정에서는 전부 허용 처리합니다.
         */
    private void permitAllRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> registry.anyRequest().authenticated());
    }

}