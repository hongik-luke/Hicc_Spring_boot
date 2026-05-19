package com.piumteo.server.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.piumteo.server.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record ApiResponse<T>(
        int status,
        String code,
        String message,
        T result
) {

    public static <T> ApiResponse<T> onSuccess(
            ErrorCode code,
            T result
    ) {
        return new ApiResponse<>(
                code.getHttpStatus().value(),
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    public static <T> ApiResponse<T> onSuccess(
            HttpStatus status,
            String code,
            String message,
            T result
    ) {
        return new ApiResponse<>(
                status.value(),
                code,
                message,
                result
        );
    }

    public static <T> ApiResponse<T> onFailure(
            ErrorCode code
    ) {
        return new ApiResponse<>(
                code.getHttpStatus().value(),
                code.getCode(),
                code.getMessage(),
                null
        );
    }

    public static <T> ApiResponse<T> onFailure(
            ErrorCode code,
            String message
    ) {
        return new ApiResponse<>(
                code.getHttpStatus().value(),
                code.getCode(),
                message,
                null
        );
    }
}