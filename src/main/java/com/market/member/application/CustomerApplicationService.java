package com.market.member.application;

import com.market.member.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerApplicationService {


    public List<CustomerDto> getCustomerList() {
        return null;
    }

    public CustomerDto getCustomer(Long id) {
        CustomerDto dto = CustomerDto.builder().build();
        return dto;
    }

    public void signUp(CustomerDto param) {

    }

    public void modifyInfo(Long id, CustomerDto param) {

    }

}