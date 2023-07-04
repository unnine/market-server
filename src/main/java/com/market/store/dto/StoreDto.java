package com.market.store.dto;

import com.market.member.dto.SellerDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreDto {

    Long id;

    String name;

    SellerDto owner;

}
