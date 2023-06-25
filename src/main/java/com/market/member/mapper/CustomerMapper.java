package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Customer;
import com.market.member.domain.vo.Phone;
import com.market.member.dto.CustomerDto;
import com.market.member.dto.CustomerModifyDto;
import com.market.member.dto.CustomerRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, uses = { PhoneMapper.class })
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto> {

    @Mapping(target = "phone", source = "phoneNumber")
    Customer toEntity(CustomerRegisterDto dto);

    default void updateEntity(CustomerModifyDto dto, Customer customer) {
        PhoneMapper phoneMapper = PhoneMapper.INSTANCE;
        Phone phone = phoneMapper.toEntity(dto.getPhoneNumber());
        customer.updateInfo(phone);
    };

}