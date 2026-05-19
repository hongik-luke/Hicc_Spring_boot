package com.piumteo.server.domain.comment.controller;

import com.piumteo.server.domain.comment.dto.CommentCursorResponse;
import com.piumteo.server.domain.comment.exception.CommentCode;
import com.piumteo.server.domain.comment.service.CommentService;
import com.piumteo.server.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "댓글",
        description = "장소 댓글 조회, 작성, 삭제 관련 API"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places/{placeId}/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "댓글 목록 조회",
            description = """
                    특정 장소의 댓글 목록을 cursorId 기반 무한스크롤 방식으로 조회합니다.
                    
                    - 첫 조회 시 cursorId는 전달하지 않습니다.
                    - 다음 페이지 조회 시 이전 응답의 nextCursor 값을 cursorId로 전달합니다.
                    - 댓글은 최신순으로 조회됩니다.
                    - 현재 로그인/인증 기능이 없으므로 isMine은 임시로 false 처리됩니다.
                    """
    )
    @GetMapping
    public ResponseEntity<ApiResponse<CommentCursorResponse>> getComments(
            @Parameter(
                    description = "댓글을 조회할 장소 ID",
                    example = "1",
                    required = true
            )
            @PathVariable @Positive Long placeId,

            @Parameter(
                    description = "마지막으로 조회한 댓글 ID. 첫 조회 시 생략합니다.",
                    example = "30"
            )
            @RequestParam(required = false) @Positive Long cursorId,

            @Parameter(
                    description = "한 번에 조회할 댓글 개수. 기본값은 10, 최대값은 50입니다.",
                    example = "10"
            )
            @RequestParam(required = false) @Min(1) @Max(50) Integer size
    ) {
        CommentCursorResponse response = commentService.getComments(
                placeId,
                cursorId,
                size,
                null
        );

        return ResponseEntity
                .status(CommentCode.COMMENT_LIST_SEARCH_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(
                        CommentCode.COMMENT_LIST_SEARCH_SUCCESS,
                        response
                ));
    }

    /*
    로그인/인증 + 현재 사용자 식별 구현 후 교체할 코드

    @GetMapping
    public ResponseEntity<ApiResponse<CommentCursorResponse>> getComments(
            @PathVariable @Positive Long placeId,
            @RequestParam(required = false) @Positive Long cursorId,
            @RequestParam(required = false) @Min(1) @Max(50) Integer size,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long currentUserId = userDetails == null
                ? null
                : userDetails.getUserId();

        CommentCursorResponse response = commentService.getComments(
                placeId,
                cursorId,
                size,
                currentUserId
        );

        return ResponseEntity
                .status(CommentCode.COMMENT_LIST_SEARCH_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(
                        CommentCode.COMMENT_LIST_SEARCH_SUCCESS,
                        response
                ));
    }
    */
}