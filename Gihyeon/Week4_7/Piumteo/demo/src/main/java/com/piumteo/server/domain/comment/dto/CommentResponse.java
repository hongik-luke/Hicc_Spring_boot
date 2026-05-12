package com.piumteo.server.domain.comment.dto;

import com.piumteo.server.domain.comment.entity.CommentAuthorType;
import com.piumteo.server.domain.comment.entity.PlaceComment;

import java.time.OffsetDateTime;

public record CommentResponse(
        Long commentId,
        CommentAuthorType authorType,
        String displayNickname,
        String content,
        OffsetDateTime createdAt
) {

    public static CommentResponse from(PlaceComment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getAuthorType(),
                comment.getDisplayNickname(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}