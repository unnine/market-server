package com.market.member.domain.repository;

import com.market.member.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    boolean existsByEmail(String email);

}
