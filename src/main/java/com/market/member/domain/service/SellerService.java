package com.market.member.domain.service;

import com.market.member.domain.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public boolean existsEmail(String email) {
        return sellerRepository.existsByEmail(email);
    }

}
