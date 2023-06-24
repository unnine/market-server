package com.market.member.domain.service;

import com.market.member.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public boolean existsEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

}
