package com.piumteo.server.domain.comment.exception;

import com.piumteo.server.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentCode implements ErrorCode {

    COMMENT_LIST_SEARCH_SUCCESS(
            HttpStatus.OK,
            "COMMENT_200_000",
            "댓글 목록 조회에 성공했습니다."
    ),

    COMMENT_UPDATE_SUCCESS(
            HttpStatus.OK,
            "COMMENT_200_001",
            "댓글 수정에 성공했습니다."
    ),

    COMMENT_DELETE_SUCCESS(
            HttpStatus.OK,
            "COMMENT_200_002",
            "댓글 삭제에 성공했습니다."
    ),

    COMMENT_CREATE_SUCCESS(
            HttpStatus.CREATED,
            "COMMENT_201_001",
            "댓글 작성에 성공했습니다."
    ),

    COMMENT_NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "COMMENT_404_001",
            "존재하지 않는 댓글입니다."
    ),

    COMMENT_UPDATE_FORBIDDEN(
            HttpStatus.FORBIDDEN,
            "COMMENT_403_001",
            "해당 댓글을 수정할 권한이 없습니다."
    ),

    COMMENT_DELETE_FORBIDDEN(
            HttpStatus.FORBIDDEN,
            "COMMENT_403_002",
            "해당 댓글을 삭제할 권한이 없습니다."
    ),

    COMMENT_PASSWORD_MISMATCH(
            HttpStatus.FORBIDDEN,
            "COMMENT_403_003",
            "댓글 비밀번호가 일치하지 않습니다."
    ),

    INVALID_COMMENT_AUTHOR_TYPE(
            HttpStatus.BAD_REQUEST,
            "COMMENT_400_001",
            "댓글 작성자 타입이 올바르지 않습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}