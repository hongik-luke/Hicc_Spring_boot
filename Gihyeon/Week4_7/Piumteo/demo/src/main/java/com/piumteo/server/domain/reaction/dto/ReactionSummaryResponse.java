package com.piumteo.server.domain.reaction.dto;

import com.piumteo.server.domain.reaction.entity.ReactionType;

public record ReactionSummaryResponse(
        Long placeId,
        long likeCount,
        long dislikeCount,
        ReactionType myReactionType
) {

    public static ReactionSummaryResponse of(
            Long placeId,
            long likeCount,
            long dislikeCount,
            ReactionType myReactionType
    ) {
        return new ReactionSummaryResponse(
                placeId,
                likeCount,
                dislikeCount,
                myReactionType
        );
    }
}