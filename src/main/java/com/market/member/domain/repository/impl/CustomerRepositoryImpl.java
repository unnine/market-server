package com.market.member.domain.repository.impl;

import com.market.member.domain.entity.Customer;
import com.market.member.domain.repository.CustomerRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.market.member.domain.entity.QCustomer.customer;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Customer> search(Pageable pageable) {
        List<Customer> result = queryFactory
                .select(Projections.fields(Customer.class,
                        customer.id,
                        customer.email,
                        customer.name,
                        customer.phone))
                .from(customer)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }
}
