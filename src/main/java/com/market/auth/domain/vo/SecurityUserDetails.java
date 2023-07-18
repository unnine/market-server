package com.market.auth.domain.vo;

import com.market.member.domain.vo.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class SecurityUserDetails implements UserDetails {

    @Serial
    private static final long serialVersionUID = -3234356210565886494L;

    private final MemberInfo info;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(info.getRole().name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return info.getPassword();
    }

    @Override
    public String getUsername() {
        return info.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return info.isLock();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return info.isEnable();
    }
}