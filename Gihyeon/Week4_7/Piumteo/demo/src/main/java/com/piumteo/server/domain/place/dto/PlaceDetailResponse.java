package com.piumteo.server.domain.place.dto;

import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.place.entity.PlaceType;

import java.math.BigDecimal;

public record PlaceDetailResponse(
        Long placeId,
        String name,
        PlaceType type,
        BigDecimal latitude,
        BigDecimal longitude,
        String locationDescription,
        long likeCount,
        long dislikeCount,
        long commentCount
) {

    public static PlaceDetailResponse of(
            Place place,
            long likeCount,
            long dislikeCount,
            long commentCount
    ) {
        return new PlaceDetailResponse(
                place.getId(),
                place.getName(),
                place.getType(),
                place.getLatitude(),
                place.getLongitude(),
                place.getLocationDescription(),
                likeCount,
                dislikeCount,
                commentCount
        );
    }
}