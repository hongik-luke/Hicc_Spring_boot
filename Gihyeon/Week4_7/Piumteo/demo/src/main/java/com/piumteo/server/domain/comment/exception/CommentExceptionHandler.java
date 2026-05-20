package com.piumteo.server.domain.comment.exception;

import com.piumteo.server.global.exception.ErrorCode;
import com.piumteo.server.global.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CommentExceptionHandler {

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<ErrorResponse> handleCommentException(
            CommentException exception
    ) {
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.of(errorCode, exception.getMessage()));
    }
}
