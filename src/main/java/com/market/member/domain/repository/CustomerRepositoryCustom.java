package com.market.member.domain.repository;

import com.market.member.domain.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<Customer> search(Pageable pageable);

}
