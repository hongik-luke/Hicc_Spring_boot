package com.piumteo.server.domain.comment.dto;

import com.piumteo.server.domain.comment.entity.CommentAuthorType;
import com.piumteo.server.domain.comment.entity.PlaceComment;

public record CommentMutationResponse(
        Long placeCommentId,
        CommentAuthorType commentAuthorType,
        String displayNickname,
        String content
) {

    public static CommentMutationResponse from(PlaceComment comment) {
        return new CommentMutationResponse(
                comment.getId(),
                comment.getAuthorType(),
                comment.getDisplayNickname(),
                comment.getContent()
        );
    }
}