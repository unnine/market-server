package com.market.auth.domain.service;

import com.market.auth.exception.InvalidPasswordException;
import com.market.auth.exception.InvalidUsernameException;
import com.market.common.util.Cipher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    private final SecurityUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());

        if (!StringUtils.hasText(username)) {
            throw new InvalidUsernameException("No exists username for authentication");
        }

        if (!StringUtils.hasText(password)) {
            throw new InvalidPasswordException("Not matched password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!Cipher.matches(password, userDetails.getPassword())) {
            throw new InvalidPasswordException("Not matched password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}