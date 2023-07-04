package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.CustomerAddress;
import com.market.member.dto.CustomerAddressDto;
import com.market.member.dto.CustomerAddressRegisterDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface CustomerAddressMapper extends BaseMapper<CustomerAddress, CustomerAddressDto> {

    CustomerAddress toEntity(CustomerAddressRegisterDto dto);

}