package com.piumteo.server.domain.place.dto;

import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.place.entity.PlaceType;

import java.math.BigDecimal;

public record PlaceSummaryResponse(
        Long placeId,
        String name,
        PlaceType type,
        BigDecimal latitude,
        BigDecimal longitude
) {

    public static PlaceSummaryResponse from(Place place) {
        return new PlaceSummaryResponse(
                place.getId(),
                place.getName(),
                place.getType(),
                place.getLatitude(),
                place.getLongitude()
        );
    }
}