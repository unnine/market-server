package com.market.common.domain.entity;

import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class BaseEntity {

    @CreatedDate
    LocalDateTime createdAt;

}