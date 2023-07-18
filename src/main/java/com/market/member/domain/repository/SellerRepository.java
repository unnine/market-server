package com.market.member.domain.repository;

import com.market.member.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Seller s WHERE s.info.email = :email")
    boolean existsByEmail(String email);

    @Query("SELECT s FROM Seller s WHERE s.info.email = :email")
    Optional<Seller> findByEmail(String email);

}