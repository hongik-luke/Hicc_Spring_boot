package com.piumteo.server.domain.reaction.service;

import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.place.service.PlaceService;
import com.piumteo.server.domain.reaction.dto.ReactPlaceRequest;
import com.piumteo.server.domain.reaction.dto.ReactionSummaryResponse;
import com.piumteo.server.domain.reaction.entity.PlaceReaction;
import com.piumteo.server.domain.reaction.entity.ReactionType;
import com.piumteo.server.domain.reaction.repository.PlaceReactionRepository;
import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.domain.user.service.UserService;
import com.piumteo.server.global.exception.BusinessException;
import com.piumteo.server.global.exception.ErrorCode;
import com.piumteo.server.global.util.HashUtils;
import com.piumteo.server.global.util.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReactionService {

    private final PlaceReactionRepository placeReactionRepository;
    private final PlaceService placeService;
    private final UserService userService;

    @Transactional
    public ReactionSummaryResponse reactAsMember(
            Long placeId,
            Long userId,
            ReactPlaceRequest request
    ) {
        validateReactionType(request.reactionType());

        Place place = placeService.getActivePlace(placeId);
        User user = userService.getActiveUser(userId);

        Long currentHourKey = TimeUtils.currentHourKey();

        Optional<PlaceReaction> optionalReaction =
                placeReactionRepository.findTopByPlace_IdAndMember_IdOrderByCreatedAtDesc(
                        placeId,
                        userId
                );

        if (optionalReaction.isPresent()) {
            PlaceReaction reaction = optionalReaction.get();
            reaction.changeReaction(request.reactionType(), currentHourKey);
        } else {
            PlaceReaction reaction = PlaceReaction.createMemberReaction(
                    place,
                    user,
                    request.reactionType(),
                    currentHourKey
            );

            placeReactionRepository.save(reaction);
        }

        return getReactionSummary(placeId, request.reactionType());
    }

    @Transactional
    public ReactionSummaryResponse reactAsGuest(
            Long placeId,
            String guestKey,
            ReactPlaceRequest request
    ) {
        validateReactionType(request.reactionType());
        validateGuestKey(guestKey);

        Place place = placeService.getActivePlace(placeId);

        String guestKeyHash = HashUtils.sha256(guestKey);
        Long currentHourKey = TimeUtils.currentHourKey();

        Optional<PlaceReaction> optionalReaction =
                placeReactionRepository.findTopByPlace_IdAndGuestKeyHashOrderByCreatedAtDesc(
                        placeId,
                        guestKeyHash
                );

        if (optionalReaction.isPresent()) {
            PlaceReaction reaction = optionalReaction.get();

            if (reaction.getReactionHourKey().equals(currentHourKey)) {
                throw new BusinessException(ErrorCode.REACTION_TOO_FAST);
            }

            reaction.changeReaction(request.reactionType(), currentHourKey);
        } else {
            PlaceReaction reaction = PlaceReaction.createGuestReaction(
                    place,
                    guestKeyHash,
                    request.reactionType(),
                    currentHourKey
            );

            placeReactionRepository.save(reaction);
        }

        return getReactionSummary(placeId, request.reactionType());
    }

    public ReactionSummaryResponse getReactionSummary(
            Long placeId,
            ReactionType myReactionType
    ) {
        long likeCount = placeReactionRepository.countByPlace_IdAndReactionType(
                placeId,
                ReactionType.LIKE
        );

        long dislikeCount = placeReactionRepository.countByPlace_IdAndReactionType(
                placeId,
                ReactionType.DISLIKE
        );

        return ReactionSummaryResponse.of(
                placeId,
                likeCount,
                dislikeCount,
                myReactionType
        );
    }

    private void validateReactionType(ReactionType reactionType) {
        if (reactionType == null) {
            throw new BusinessException(ErrorCode.INVALID_REACTION_TYPE);
        }
    }

    private void validateGuestKey(String guestKey) {
        if (guestKey == null || guestKey.isBlank()) {
            throw new BusinessException(
                    ErrorCode.INVALID_REQUEST,
                    "게스트 식별 키가 필요합니다."
            );
        }
    }
}