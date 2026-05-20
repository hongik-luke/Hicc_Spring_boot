package com.piumteo.server.global.security.exception;

import com.piumteo.server.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SecurityErrorCode implements ErrorCode {

    UNAUTHORIZED(
            HttpStatus.UNAUTHORIZED,
            "SECURITY_401_001",
            "인증이 필요합니다."
    ),

    INVALID_ACCESS_TOKEN(
            HttpStatus.UNAUTHORIZED,
            "SECURITY_401_002",
            "유효하지 않은 액세스 토큰입니다."
    ),

    EXPIRED_ACCESS_TOKEN(
            HttpStatus.UNAUTHORIZED,
            "SECURITY_401_003",
            "만료된 액세스 토큰입니다."
    ),

    ACCESS_DENIED(
            HttpStatus.FORBIDDEN,
            "SECURITY_403_001",
            "접근 권한이 없습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
