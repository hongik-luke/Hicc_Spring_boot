package com.piumteo.server.domain.comment.dto;

import com.piumteo.server.domain.comment.entity.CommentAuthorType;
import com.piumteo.server.domain.comment.entity.PlaceComment;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

@Schema(description = "댓글 응답")
public record CommentResponse(

        @Schema(
                description = "댓글 ID",
                example = "15",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long placeCommentId,

        @Schema(
                description = "댓글 작성자 타입",
                example = "GUEST",
                allowableValues = {"MEMBER", "GUEST"},
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        CommentAuthorType commentAuthorType,

        @Schema(
                description = "화면에 표시할 작성자 이름",
                example = "지나가던사람",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String displayNickname,

        @Schema(
                description = "댓글 내용",
                example = "여기 실제로 흡연구역 맞습니다.",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String content,

        @Schema(
                description = "댓글 작성 시각",
                example = "2026-05-04T12:00:00Z",
                type = "string",
                format = "date-time",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        OffsetDateTime createdAt,

        @Schema(
                description = "댓글 수정 시각. 수정되지 않았으면 createdAt과 동일합니다.",
                example = "2026-05-04T12:00:00Z",
                type = "string",
                format = "date-time",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        OffsetDateTime updatedAt,

        @Schema(
                description = "현재 로그인 사용자가 작성한 댓글인지 여부. 현재 인증 미구현 상태에서는 false입니다.",
                example = "false",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        boolean isMine
) {

    public static CommentResponse from(
            PlaceComment comment,
            boolean isMine
    ) {
        return new CommentResponse(
                comment.getId(),
                comment.getAuthorType(),
                comment.getDisplayNickname(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                isMine
        );
    }
}