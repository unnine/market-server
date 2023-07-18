package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.entity.Seller;
import com.market.member.domain.vo.Phone;
import com.market.member.dto.SellerDto;
import com.market.member.dto.SellerModifyDto;
import com.market.member.dto.SellerRegisterDto;
import com.market.store.mapper.StoreMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class, uses = {
        StoreMapper.class,
        MemberInfoMapper.class
})
public interface SellerMapper extends BaseMapper<Seller, SellerDto> {

    @Override
    Seller toEntity(SellerDto dto);

    @Mapping(target = "info", source = "dto")
    Seller toEntity(SellerRegisterDto dto);

}