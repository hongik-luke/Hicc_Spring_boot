package com.piumteo.server.domain.place.exception;

import com.piumteo.server.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PlaceErrorCode implements ErrorCode {

    PLACE_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "PLACE_404_001",
            "장소를 찾을 수 없습니다."
    ),

    INVALID_PLACE_TYPE(
            HttpStatus.BAD_REQUEST,
            "PLACE_400_001",
            "장소 타입이 올바르지 않습니다."
    ),

    INVALID_PLACE_COORDINATE(
            HttpStatus.BAD_REQUEST,
            "PLACE_400_002",
            "장소 좌표가 올바르지 않습니다."
    ),

    INVALID_BOUNDS(
            HttpStatus.BAD_REQUEST,
            "PLACE_400_003",
            "지도 영역 좌표가 올바르지 않습니다."
    ),

    PLACE_ALREADY_DELETED(
            HttpStatus.CONFLICT,
            "PLACE_409_001",
            "이미 삭제된 장소입니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
