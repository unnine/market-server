package com.market.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationConfigurer implements SecurityConfigurer {

    private final String loginUrl = "/login";
    private final String logoutUrl = "/logout";

    @Override
    public void config(HttpSecurity http) throws Exception {
        disableConfig(http);
        loginConfig(http);
        logoutConfig(http);
        sessionConfig(http);

        http.securityContext(securityContext -> securityContext.securityContextRepository(new HttpSessionSecurityContextRepository()));
    }

    private void disableConfig(HttpSecurity http) throws Exception {
        http
                .httpBasic(HttpBasicConfigurer::disable)
                .csrf(CsrfConfigurer::disable)
                .rememberMe(RememberMeConfigurer::disable)
                .cors(CorsConfigurer::disable);
    }

    private void loginConfig(HttpSecurity http) throws Exception {
        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                .loginPage(loginUrl)
                .usernameParameter("username")
                .passwordParameter("password")
        );
    }

    private void logoutConfig(HttpSecurity http) throws Exception {
        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl(loginUrl)
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES)))
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