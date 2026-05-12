package com.piumteo.server.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.piumteo.server.global.exception.ErrorCode;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorResponse(
        boolean success,
        String code,
        String message,
        List<FieldError> errors,
        OffsetDateTime timestamp
) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                List.of(),
                OffsetDateTime.now()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, String message) {
        return new ErrorResponse(
                false,
                errorCode.getCode(),
                message,
                List.of(),
                OffsetDateTime.now()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
        return new ErrorResponse(
                false,
                errorCode.getCode(),
                errorCode.getMessage(),
                errors,
                OffsetDateTime.now()
        );
    }

    public record FieldError(
            String field,
            String message,
            Object rejectedValue
    ) {

        public static FieldError of(String field, String message, Object rejectedValue) {
            return new FieldError(field, message, rejectedValue);
        }
    }
}
