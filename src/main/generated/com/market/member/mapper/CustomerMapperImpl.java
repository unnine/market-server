package com.market.member.mapper;

import com.market.member.domain.entity.Customer;
import com.market.member.dto.CustomerDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-22T17:58:37+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.3 (Azul Systems, Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( dto.getId() );
        customer.email( dto.getEmail() );
        customer.name( dto.getName() );
        customer.phone( dto.getPhone() );

        return customer.build();
    }

    @Override
    public CustomerDto toDto(Customer entity) {
        if ( entity == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( entity.getId() );
        customerDto.email( entity.getEmail() );
        customerDto.name( entity.getName() );
        customerDto.phone( entity.getPhone() );

        return customerDto.build();
    }
}
