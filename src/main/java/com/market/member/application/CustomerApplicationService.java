package com.market.member.application;

import com.market.member.dto.CustomerDto;

public class CustomerApplicationService {

    public CustomerDto getCustomer(Long id) {
        CustomerDto dto = CustomerDto.builder().build();
        return dto;
    }

}