package com.piumteo.server.domain.reaction.dto;

import com.piumteo.server.domain.reaction.entity.ReactionType;
import jakarta.validation.constraints.NotNull;

public record ReactPlaceRequest(

        @NotNull(message = "반응 타입은 필수입니다.")
        ReactionType reactionType
) {
}