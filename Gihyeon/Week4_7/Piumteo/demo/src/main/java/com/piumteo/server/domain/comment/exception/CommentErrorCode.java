package com.piumteo.server.domain.comment.exception;

import com.piumteo.server.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    COMMENT_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "COMMENT_404_001",
            "댓글을 찾을 수 없습니다."
    ),

    COMMENT_ALREADY_DELETED(
            HttpStatus.CONFLICT,
            "COMMENT_409_001",
            "이미 삭제된 댓글입니다."
    ),

    INVALID_GUEST_PASSWORD(
            HttpStatus.BAD_REQUEST,
            "COMMENT_400_001",
            "게스트 비밀번호가 일치하지 않습니다."
    ),

    UNAUTHORIZED_COMMENT_DELETE(
            HttpStatus.FORBIDDEN,
            "COMMENT_403_001",
            "댓글을 삭제할 권한이 없습니다."
    ),

    INVALID_COMMENT_AUTHOR_TYPE(
            HttpStatus.BAD_REQUEST,
            "COMMENT_400_002",
            "댓글 작성자 타입이 올바르지 않습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
