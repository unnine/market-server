package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Customer;
import com.market.member.dto.CustomerDto;
import com.market.member.dto.CustomerRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, uses = {
        MemberInfoMapper.class
})
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto> {

    @Mapping(target = "info", source = "dto")
    Customer toEntity(CustomerRegisterDto dto);

}