package com.piumteo.server.domain.place.dto;

public record CreatePlaceResponse(
        Long placeId
) {

    public static CreatePlaceResponse from(Long placeId) {
        return new CreatePlaceResponse(placeId);
    }
}