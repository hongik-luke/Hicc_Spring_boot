package com.piumteo.server.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateGuestCommentRequest(

        @NotBlank(message = "게스트 비밀번호는 필수입니다.")
        @Size(min = 4, max = 50, message = "게스트 비밀번호는 4자 이상 50자 이하여야 합니다.")
        String guestPassword,

        @NotBlank(message = "댓글 내용은 필수입니다.")
        @Size(max = 500, message = "댓글은 500자 이하여야 합니다.")
        String content
) {
}