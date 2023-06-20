package com.market.member.domain.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Phone {

    @NotBlank
    String phoneNumber;

    public Phone(String phoneNumber) {

        // TODO 번호 유효성 체크

        this.phoneNumber = phoneNumber;
    }
}