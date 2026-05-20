package com.piumteo.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    INVALID_REQUEST(
            HttpStatus.BAD_REQUEST,
            "COMMON_400_001",
            "잘못된 요청입니다."
    ),

    INVALID_INPUT_VALUE(
            HttpStatus.BAD_REQUEST,
            "COMMON_400_002",
            "입력값이 올바르지 않습니다."
    ),

    METHOD_NOT_ALLOWED(
            HttpStatus.METHOD_NOT_ALLOWED,
            "COMMON_405_001",
            "허용되지 않은 HTTP 메서드입니다."
    ),

    INTERNAL_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON_500_001",
            "서버 내부 오류가 발생했습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
