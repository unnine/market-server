package com.market.member.mapper;

import com.market.member.domain.entity.Customer;
import com.market.member.dto.CustomerDto;
import com.market.member.dto.CustomerRegisterDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-04T16:05:26+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.3 (Azul Systems, Inc.)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Autowired
    private PhoneMapper phoneMapper;

    @Override
    public Customer toEntity(CustomerDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( arg0.getId() );
        customer.email( arg0.getEmail() );
        customer.name( arg0.getName() );
        customer.phone( arg0.getPhone() );

        return customer.build();
    }

    @Override
    public CustomerDto toDto(Customer arg0) {
        if ( arg0 == null ) {
            return null;
        }

        CustomerDto.CustomerDtoBuilder customerDto = CustomerDto.builder();

        customerDto.id( arg0.getId() );
        customerDto.email( arg0.getEmail() );
        customerDto.name( arg0.getName() );
        customerDto.phone( arg0.getPhone() );

        return customerDto.build();
    }

    @Override
    public Customer toEntity(CustomerRegisterDto dto) {
        if ( dto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.phone( phoneMapper.toEntity( dto.getPhoneNumber() ) );
        customer.email( dto.getEmail() );
        customer.name( dto.getName() );

        return customer.build();
    }
}
