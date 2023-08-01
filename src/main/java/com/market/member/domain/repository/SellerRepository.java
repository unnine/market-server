package com.market.member.domain.repository;

import com.market.member.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Seller s WHERE s.info.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT s FROM Seller s WHERE s.info.email = :email")
    Optional<Seller> findByEmail(@Param("email") String email);

}