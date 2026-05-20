package com.piumteo.server.global.exception;

import com.piumteo.server.domain.comment.exception.CommentCode;
import com.piumteo.server.domain.place.exception.PlaceErrorCode;
import com.piumteo.server.domain.reaction.exception.ReactionErrorCode;
import com.piumteo.server.domain.user.exception.UserErrorCode;
import org.springframework.http.HttpStatus;

public interface ErrorCode {

    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();

    /**
     * TODO: Service 계층을 도메인별 Exception으로 교체한 뒤 제거할 호환용 alias.
     * 현재 service/entity/repository 파일은 건드리지 않기 위해 기존 ErrorCode.X 사용 방식을 유지한다.
     */
    @Deprecated ErrorCode USER_NOT_FOUND = UserErrorCode.USER_NOT_FOUND;
    @Deprecated ErrorCode DUPLICATE_EMAIL = UserErrorCode.DUPLICATE_EMAIL;
    @Deprecated ErrorCode DUPLICATE_NICKNAME = UserErrorCode.DUPLICATE_NICKNAME;
    @Deprecated ErrorCode WITHDRAWN_USER = UserErrorCode.WITHDRAWN_USER;

    @Deprecated ErrorCode PLACE_NOT_FOUND = PlaceErrorCode.PLACE_NOT_FOUND;
    @Deprecated ErrorCode INVALID_PLACE_TYPE = PlaceErrorCode.INVALID_PLACE_TYPE;
    @Deprecated ErrorCode INVALID_PLACE_COORDINATE = PlaceErrorCode.INVALID_PLACE_COORDINATE;
    @Deprecated ErrorCode INVALID_BOUNDS = PlaceErrorCode.INVALID_BOUNDS;
    @Deprecated ErrorCode PLACE_ALREADY_DELETED = PlaceErrorCode.PLACE_ALREADY_DELETED;

    @Deprecated ErrorCode COMMENT_NOT_FOUND = CommentCode.COMMENT_NOT_FOUND;
    @Deprecated ErrorCode COMMENT_ALREADY_DELETED = CommentCode.COMMENT_ALREADY_DELETED;
    @Deprecated ErrorCode INVALID_GUEST_PASSWORD = CommentCode.INVALID_GUEST_PASSWORD;
    @Deprecated ErrorCode UNAUTHORIZED_COMMENT_DELETE = CommentCode.UNAUTHORIZED_COMMENT_DELETE;
    @Deprecated ErrorCode INVALID_COMMENT_AUTHOR_TYPE = CommentCode.INVALID_COMMENT_AUTHOR_TYPE;

    @Deprecated ErrorCode INVALID_REACTION_TYPE = ReactionErrorCode.INVALID_REACTION_TYPE;
    @Deprecated ErrorCode REACTION_TOO_FAST = ReactionErrorCode.REACTION_TOO_FAST;
    @Deprecated ErrorCode REACTION_NOT_FOUND = ReactionErrorCode.REACTION_NOT_FOUND;
    @Deprecated ErrorCode INVALID_REACTION_AUTHOR_TYPE = ReactionErrorCode.INVALID_REACTION_AUTHOR_TYPE;

    @Deprecated ErrorCode INVALID_REQUEST = CommonErrorCode.INVALID_REQUEST;
    @Deprecated ErrorCode INVALID_INPUT_VALUE = CommonErrorCode.INVALID_INPUT_VALUE;
    @Deprecated ErrorCode METHOD_NOT_ALLOWED = CommonErrorCode.METHOD_NOT_ALLOWED;
    @Deprecated ErrorCode INTERNAL_SERVER_ERROR = CommonErrorCode.INTERNAL_SERVER_ERROR;
}
