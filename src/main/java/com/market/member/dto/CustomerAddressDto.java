package com.market.member.dto;

import com.market.common.domain.vo.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class CustomerAddressDto {

    Long id;

    Address address;

}