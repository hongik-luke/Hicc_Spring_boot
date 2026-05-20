package com.piumteo.server.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DeleteGuestCommentRequest(

        @NotBlank(message = "게스트 비밀번호는 필수입니다.")
        @Size(min = 4, max = 30, message = "게스트 비밀번호는 4자 이상 30자 이하여야 합니다.")
        String guestPassword
) {
}