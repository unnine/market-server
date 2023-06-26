package com.market.store.mapper;

import com.market.common.mapper.BaseMapper;
import com.market.common.mapper.BaseMapperConfig;
import com.market.store.domain.entity.Item;
import com.market.store.dto.ItemDto;
import com.market.store.dto.ItemModifyDto;
import com.market.store.dto.ItemRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        config = BaseMapperConfig.class,
        uses = { StoreMapper.class }
)
public interface ItemMapper extends BaseMapper<Item, ItemDto> {

    Item toEntity(ItemRegisterDto dto);

    void updateEntity(ItemModifyDto dto, @MappingTarget Item item);

}