package com.market.member.mapper;

import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.vo.MemberInfo;
import com.market.member.dto.CustomerRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class, uses = {
        PhoneMapper.class
})
public interface MemberInfoMapper {

    @Mapping(target = "phone", source = "phoneNumber")
    MemberInfo toInfo(CustomerRegisterDto dto);



}