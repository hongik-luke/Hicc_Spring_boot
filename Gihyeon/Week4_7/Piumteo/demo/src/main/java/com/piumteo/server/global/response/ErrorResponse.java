package com.piumteo.server.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.piumteo.server.global.exception.ErrorCode;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record ErrorResponse(
        int status,
        String code,
        String message,
        Object result
) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                null
        );
    }

    public static ErrorResponse of(
            ErrorCode errorCode,
            String message
    ) {
        return new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                message,
                null
        );
    }
}