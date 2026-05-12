package com.piumteo.server.domain.place.dto;

import com.piumteo.server.domain.place.entity.PlaceType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreatePlaceRequest(

        @NotBlank(message = "장소 이름은 필수입니다.")
        @Size(max = 100, message = "장소 이름은 100자 이하여야 합니다.")
        String name,

        @NotNull(message = "장소 타입은 필수입니다.")
        PlaceType type,

        @NotNull(message = "위도는 필수입니다.")
        @DecimalMin(value = "-90.0", message = "위도는 -90 이상이어야 합니다.")
        @DecimalMax(value = "90.0", message = "위도는 90 이하여야 합니다.")
        BigDecimal latitude,

        @NotNull(message = "경도는 필수입니다.")
        @DecimalMin(value = "-180.0", message = "경도는 -180 이상이어야 합니다.")
        @DecimalMax(value = "180.0", message = "경도는 180 이하여야 합니다.")
        BigDecimal longitude,

        @Size(max = 255, message = "위치 설명은 255자 이하여야 합니다.")
        String locationDescription
) {
}