package com.market.member.dto;

import com.market.member.domain.vo.Phone;
import com.market.store.dto.StoreDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    Long id;

    @Email
    String email;

    @NotBlank
    String name;

    @NotNull
    Phone phone;

    List<StoreDto> stores;

}
