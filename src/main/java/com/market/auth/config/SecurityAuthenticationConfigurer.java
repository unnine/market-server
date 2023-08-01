package com.market.auth.config;

import com.market.auth.domain.service.SecurityAuthenticationFailureHandler;
import com.market.auth.domain.service.SecurityAuthenticationProvider;
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

    private final String loginPageUrl = "/login";
    private final String logoutUrl = "/logout";

    private final SecurityAuthenticationProvider authenticationProvider;
    private final SecurityAuthenticationFailureHandler authenticationFailureHandler;

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
        http.authenticationProvider(authenticationProvider);

        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                .loginPage(loginPageUrl)
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureHandler(authenticationFailureHandler)
        );
    }

    private void logoutConfig(HttpSecurity http) throws Exception {
        ClearSiteDataHeaderWriter headerWriter = new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.COOKIES);
        HeaderWriterLogoutHandler logoutHandler = new HeaderWriterLogoutHandler(headerWriter);

        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl(loginPageUrl)
                .addLogoutHandler(logoutHandler));
    }

    private void sessionConfig(HttpSecurity http) throws Exception {
        http.sessionManagement(configurer -> configurer
                .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::changeSessionId)
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl(loginPageUrl)
                .maximumSessions(-1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl(loginPageUrl)
        );
    }

}