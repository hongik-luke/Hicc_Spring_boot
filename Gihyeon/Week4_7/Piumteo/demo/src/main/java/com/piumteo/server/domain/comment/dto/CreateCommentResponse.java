package com.piumteo.server.domain.comment.dto;

public record CreateCommentResponse(
        Long commentId
) {

    public static CreateCommentResponse from(Long commentId) {
        return new CreateCommentResponse(commentId);
    }
}