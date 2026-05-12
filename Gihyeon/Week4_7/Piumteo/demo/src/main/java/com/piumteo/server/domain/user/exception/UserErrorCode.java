package com.piumteo.server.domain.user.exception;

import com.piumteo.server.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

    USER_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "USER_404_001",
            "사용자를 찾을 수 없습니다."
    ),

    DUPLICATE_EMAIL(
            HttpStatus.CONFLICT,
            "USER_409_001",
            "이미 사용 중인 이메일입니다."
    ),

    DUPLICATE_NICKNAME(
            HttpStatus.CONFLICT,
            "USER_409_002",
            "이미 사용 중인 닉네임입니다."
    ),

    WITHDRAWN_USER(
            HttpStatus.FORBIDDEN,
            "USER_403_001",
            "탈퇴한 사용자입니다."
    ),

    BANNED_USER(
            HttpStatus.FORBIDDEN,
            "USER_403_002",
            "정지된 사용자입니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
