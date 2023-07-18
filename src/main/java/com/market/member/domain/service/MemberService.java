package com.market.member.domain.service;

import com.market.member.domain.entity.Customer;
import com.market.member.domain.entity.Seller;
import com.market.member.domain.repository.CustomerRepository;
import com.market.member.domain.repository.SellerRepository;
import com.market.member.domain.vo.MemberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    public boolean existsEmail(String email) {
        return customerRepository.existsByEmail(email) || sellerRepository.existsByEmail(email);
    }

    public MemberInfo findInfoByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        Seller seller = sellerRepository.findByEmail(email).orElse(null);

        if (customer != null) {
            return customer.getInfo();
        }
        if (seller != null) {
            return seller.getInfo();
        }
        throw new EmptyResultDataAccessException(1);
    }

}