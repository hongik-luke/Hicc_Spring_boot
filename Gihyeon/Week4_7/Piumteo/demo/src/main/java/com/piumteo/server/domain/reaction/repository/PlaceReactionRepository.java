package com.piumteo.server.domain.reaction.repository;

import com.piumteo.server.domain.reaction.entity.PlaceReaction;
import com.piumteo.server.domain.reaction.entity.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceReactionRepository extends JpaRepository<PlaceReaction, Long> {

    Optional<PlaceReaction> findTopByPlace_IdAndMember_IdOrderByCreatedAtDesc(
            Long placeId,
            Long memberId
    );

    Optional<PlaceReaction> findTopByPlace_IdAndGuestKeyHashOrderByCreatedAtDesc(
            Long placeId,
            String guestKeyHash
    );

    boolean existsByPlace_IdAndGuestKeyHashAndReactionHourKey(
            Long placeId,
            String guestKeyHash,
            Long reactionHourKey
    );

    long countByPlace_IdAndReactionType(
            Long placeId,
            ReactionType reactionType
    );
}