package com.market.member.dto;

import com.market.common.domain.vo.Address;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressRegisterDto {

    @Valid
    Address address;

}