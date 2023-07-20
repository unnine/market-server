package com.market.auth.domain.service;

import com.market.auth.domain.vo.SecurityUserDetails;
import com.market.member.domain.service.MemberService;
import com.market.member.domain.vo.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberInfo info = memberService.findInfoByEmail(username);

        if (info == null) {
            throw new UsernameNotFoundException("Not found user matched username. '" + username + "'");
        }

        return new SecurityUserDetails(info);
    }
}