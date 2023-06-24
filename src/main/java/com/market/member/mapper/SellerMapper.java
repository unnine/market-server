package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Seller;
import com.market.member.dto.SellerDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface SellerMapper extends BaseMapper<Seller, SellerDto> {
}
