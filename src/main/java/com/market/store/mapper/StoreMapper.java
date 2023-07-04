package com.market.store.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Seller;
import com.market.store.domain.entity.Store;
import com.market.store.dto.StoreDto;
import com.market.store.dto.StoreModifyDto;
import com.market.store.dto.StoreRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface StoreMapper extends BaseMapper<Store, StoreDto> {

    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "seller", target = "owner")
    Store toEntityForRegister(StoreRegisterDto dto, Seller seller);

}