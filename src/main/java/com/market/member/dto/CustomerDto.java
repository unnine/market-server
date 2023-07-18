package com.market.member.dto;

import com.market.member.domain.vo.Phone;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDto {

    Long id;

    String email;

    String name;

    Phone phone;

    boolean lock;

    boolean enable;

}