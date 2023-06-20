package com.market.member.application;

import com.market.member.domain.entity.Customer;
import com.market.member.domain.repository.CustomerRepository;
import com.market.member.dto.CustomerDto;
import com.market.member.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public List<CustomerDto> getCustomerList() {
        return null;
    }

    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
        return customerMapper.toDto(customer);
    }

    public void signUp(CustomerDto param) {
        Customer newCustomer = customerMapper.toEntity(param);
        customerRepository.save(newCustomer);
    }

    public void modifyInfo(Long id, CustomerDto param) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        customer.updateInfo(param);
    }

}