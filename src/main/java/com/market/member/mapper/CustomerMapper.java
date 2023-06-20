package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Customer;
import com.market.member.dto.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface CustomerMapper extends BaseMapper<Customer, CustomerDto> {
}