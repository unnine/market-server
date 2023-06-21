package com.market.member.dto;

import com.market.member.domain.vo.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    Long id;

    @Email
    String email;

    @NotBlank
    String name;

    @NotNull
    Phone phone;

}