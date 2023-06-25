package com.market.member.dto;

import com.market.member.domain.vo.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    Long id;

    String email;

    String name;

    Phone phone;

}
