package com.common.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Phone {

    String phone;

    public Phone(String phone) {

        // TODO 번호 유효성 체크

        this.phone = phone;
    }
}