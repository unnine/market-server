package com.market.common.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    String roadAddress;

    String roadEngAddress;

    String roadPartAddress;

    String roadReferenceAddress;

    String manualAddress;

    String jibunAddress;

    String controlCenterAddress;

    String zipNo;

    String siName;

    String guName;

    String dongName;

    String dongNo;

    String liName;

    String roadName;

    String districtCode;

    String roadCode;

    String buildingManagementCode;

    String buildingName;

    String buildingNameDetail;

    boolean mountain;

    boolean underground;

    boolean cohousing;

}