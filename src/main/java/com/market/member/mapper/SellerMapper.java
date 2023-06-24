package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Seller;
import com.market.member.dto.SellerDto;
import com.market.store.mapper.StoreMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class, uses = { StoreMapper.class })
public interface SellerMapper extends BaseMapper<Seller, SellerDto> {
}
