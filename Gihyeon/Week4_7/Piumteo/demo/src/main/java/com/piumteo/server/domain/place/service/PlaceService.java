package com.piumteo.server.domain.place.service;

import com.piumteo.server.domain.comment.repository.PlaceCommentRepository;
import com.piumteo.server.domain.place.dto.BoundsRequest;
import com.piumteo.server.domain.place.dto.CreatePlaceRequest;
import com.piumteo.server.domain.place.dto.CreatePlaceResponse;
import com.piumteo.server.domain.place.dto.PlaceDetailResponse;
import com.piumteo.server.domain.place.dto.PlaceSummaryResponse;
import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.place.repository.PlaceRepository;
import com.piumteo.server.domain.reaction.entity.ReactionType;
import com.piumteo.server.domain.reaction.repository.PlaceReactionRepository;
import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.domain.user.service.UserService;
import com.piumteo.server.global.exception.BusinessException;
import com.piumteo.server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final UserService userService;
    private final PlaceReactionRepository placeReactionRepository;
    private final PlaceCommentRepository placeCommentRepository;

    @Transactional
    public CreatePlaceResponse createPlace(
            Long userId,
            CreatePlaceRequest request
    ) {
        User user = userService.getActiveUser(userId);

        Place place = new Place(
                user,
                request.name(),
                request.type(),
                request.latitude(),
                request.longitude(),
                request.locationDescription()
        );

        Place savedPlace = placeRepository.save(place);

        return CreatePlaceResponse.from(savedPlace.getId());
    }

    public List<PlaceSummaryResponse> getPlacesInBounds(BoundsRequest request) {
        validateBounds(request);

        return placeRepository
                .findByDeletedAtIsNullAndLatitudeBetweenAndLongitudeBetween(
                        request.minLatitude(),
                        request.maxLatitude(),
                        request.minLongitude(),
                        request.maxLongitude()
                )
                .stream()
                .map(PlaceSummaryResponse::from)
                .toList();
    }

    public PlaceDetailResponse getPlaceDetail(Long placeId) {
        Place place = getActivePlace(placeId);

        long likeCount = placeReactionRepository.countByPlace_IdAndReactionType(
                placeId,
                ReactionType.LIKE
        );

        long dislikeCount = placeReactionRepository.countByPlace_IdAndReactionType(
                placeId,
                ReactionType.DISLIKE
        );

        long commentCount = placeCommentRepository.countByPlace_IdAndDeletedAtIsNull(placeId);

        return PlaceDetailResponse.of(
                place,
                likeCount,
                dislikeCount,
                commentCount
        );
    }

    public Place getActivePlace(Long placeId) {
        return placeRepository.findByIdAndDeletedAtIsNull(placeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.PLACE_NOT_FOUND));
    }

    private void validateBounds(BoundsRequest request) {
        if (isGreaterThan(request.minLatitude(), request.maxLatitude())) {
            throw new BusinessException(
                    ErrorCode.INVALID_BOUNDS,
                    "최소 위도는 최대 위도보다 클 수 없습니다."
            );
        }

        if (isGreaterThan(request.minLongitude(), request.maxLongitude())) {
            throw new BusinessException(
                    ErrorCode.INVALID_BOUNDS,
                    "최소 경도는 최대 경도보다 클 수 없습니다."
            );
        }
    }

    private boolean isGreaterThan(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }
}