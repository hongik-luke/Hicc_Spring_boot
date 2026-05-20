package com.piumteo.server.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "댓글 커서 페이지 응답")
public record CommentCursorResponse(

        @Schema(
                description = "댓글 목록",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        List<CommentResponse> comments,

        @Schema(
                description = "다음 페이지 조회에 사용할 cursorId. 다음 댓글이 없으면 null입니다.",
                example = "10",
                nullable = true
        )
        Long nextCursor,

        @Schema(
                description = "다음 페이지 존재 여부",
                example = "true",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        boolean hasNext
) {
}