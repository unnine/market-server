package com.market.member.domain.repository;

import com.market.member.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Customer c WHERE c.info.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE c.info.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);

}