package com.piumteo.server.domain.reaction.exception;

import com.piumteo.server.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReactionErrorCode implements ErrorCode {

    INVALID_REACTION_TYPE(
            HttpStatus.BAD_REQUEST,
            "REACTION_400_001",
            "반응 타입이 올바르지 않습니다."
    ),

    REACTION_TOO_FAST(
            HttpStatus.TOO_MANY_REQUESTS,
            "REACTION_429_001",
            "같은 장소에 1시간 내 중복 반응할 수 없습니다."
    ),

    REACTION_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REACTION_404_001",
            "반응 정보를 찾을 수 없습니다."
    ),

    INVALID_REACTION_AUTHOR_TYPE(
            HttpStatus.BAD_REQUEST,
            "REACTION_400_002",
            "반응 작성자 타입이 올바르지 않습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
