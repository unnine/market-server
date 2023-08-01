package com.market.member.domain.vo;

import com.market.auth.domain.vo.Role;
import com.market.common.util.Cipher;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo {

    @Email
    String email;

    @NotBlank
    String name;

    @NotBlank
    String password;

    @NotNull
    @Embedded
    Phone phone;

    @Enumerated(EnumType.STRING)
    Role role;

    boolean lock;

    boolean enable;

    public void updatePhone(Phone phone) {
        this.phone = phone;
    }

    public boolean isEmpty() {
        return !StringUtils.hasText(email);
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}