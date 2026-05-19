package com.piumteo.server.domain.comment.controller;

import com.piumteo.server.domain.comment.dto.CommentCursorResponse;
import com.piumteo.server.domain.comment.dto.CommentMutationResponse;
import com.piumteo.server.domain.comment.dto.CreateGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.DeleteGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.UpdateGuestCommentRequest;
import com.piumteo.server.domain.comment.exception.CommentCode;
import com.piumteo.server.domain.comment.service.CommentService;
import com.piumteo.server.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "댓글",
        description = "장소 댓글 조회, 작성, 수정, 삭제 관련 API"
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

    @Operation(
            summary = "비회원 댓글 작성",
            description = """
                    특정 장소에 비회원 댓글을 작성합니다.
                    
                    - 현재 로그인/인증 기능이 없으므로 비회원 댓글 작성만 우선 지원합니다.
                    - displayNickname, guestPassword, content가 필요합니다.
                    - guestPassword는 서버에서 해시 처리하여 저장합니다.
                    
                    TODO:
                    - 인증 구현 후 accessToken이 있으면 회원 댓글 작성으로 분기합니다.
                    - 회원 댓글 작성 시 displayNickname은 회원 닉네임을 복사하여 저장합니다.
                    """
    )
    @PostMapping
    public ResponseEntity<ApiResponse<CommentMutationResponse>> createGuestComment(
            @Parameter(
                    description = "댓글을 작성할 장소 ID",
                    example = "1",
                    required = true
            )
            @PathVariable @Positive Long placeId,

            @Valid @RequestBody CreateGuestCommentRequest request
    ) {
        CommentMutationResponse response = commentService.createGuestComment(
                placeId,
                request
        );

        return ResponseEntity
                .status(CommentCode.COMMENT_CREATE_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(
                        CommentCode.COMMENT_CREATE_SUCCESS,
                        response
                ));
    }

    @Operation(
            summary = "비회원 댓글 수정",
            description = """
                    특정 장소의 비회원 댓글 내용을 수정합니다.
                    
                    - 현재 로그인/인증 기능이 없으므로 비회원 댓글 수정만 우선 지원합니다.
                    - guestPassword가 기존 댓글 비밀번호와 일치해야 수정할 수 있습니다.
                    - commentId가 placeId에 속한 댓글인지 함께 검증합니다.
                    
                    TODO:
                    - 인증 구현 후 회원 댓글이면 현재 로그인 사용자 ID와 댓글 작성자 ID를 비교합니다.
                    - 회원 댓글 수정 시 guestPassword 없이 content만 받도록 분기합니다.
                    """
    )
    @PatchMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CommentMutationResponse>> updateGuestComment(
            @Parameter(
                    description = "댓글이 속한 장소 ID",
                    example = "1",
                    required = true
            )
            @PathVariable @Positive Long placeId,

            @Parameter(
                    description = "수정할 댓글 ID",
                    example = "15",
                    required = true
            )
            @PathVariable @Positive Long commentId,

            @Valid @RequestBody UpdateGuestCommentRequest request
    ) {
        CommentMutationResponse response = commentService.updateGuestComment(
                placeId,
                commentId,
                request
        );

        return ResponseEntity
                .status(CommentCode.COMMENT_UPDATE_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(
                        CommentCode.COMMENT_UPDATE_SUCCESS,
                        response
                ));
    }

    @Operation(
            summary = "비회원 댓글 삭제",
            description = """
                    특정 장소의 비회원 댓글을 삭제합니다.
                    
                    - 현재 로그인/인증 기능이 없으므로 비회원 댓글 삭제만 우선 지원합니다.
                    - guestPassword가 기존 댓글 비밀번호와 일치해야 삭제할 수 있습니다.
                    - 실제 DB row를 제거하지 않고 deletedAt을 채우는 soft delete 방식입니다.
                    - commentId가 placeId에 속한 댓글인지 함께 검증합니다.
                    
                    TODO:
                    - 인증 구현 후 회원 댓글이면 현재 로그인 사용자 ID와 댓글 작성자 ID를 비교합니다.
                    - 회원 댓글 삭제 시 request body 없이 삭제할 수 있도록 분기합니다.
                    """
    )
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteGuestComment(
            @Parameter(
                    description = "댓글이 속한 장소 ID",
                    example = "1",
                    required = true
            )
            @PathVariable @Positive Long placeId,

            @Parameter(
                    description = "삭제할 댓글 ID",
                    example = "15",
                    required = true
            )
            @PathVariable @Positive Long commentId,

            @Valid @RequestBody DeleteGuestCommentRequest request
    ) {
        commentService.deleteGuestComment(
                placeId,
                commentId,
                request
        );

        return ResponseEntity
                .status(CommentCode.COMMENT_DELETE_SUCCESS.getHttpStatus())
                .body(ApiResponse.onSuccess(
                        CommentCode.COMMENT_DELETE_SUCCESS,
                        null
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