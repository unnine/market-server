package com.market.member.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.member.domain.vo.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public interface PhoneMapper extends BaseMapper<Phone, String> {
    PhoneMapper INSTANCE = Mappers.getMapper( PhoneMapper.class );

    @Override
    default Phone toEntity(String phoneNumber) {
        return new Phone(phoneNumber);
    };

    @Override
    default String toDto(Phone entity) {
        return entity.getPhoneNumber();
    }

}
