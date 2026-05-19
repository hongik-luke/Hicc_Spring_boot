package com.piumteo.server.domain.comment.service;

import com.piumteo.server.domain.comment.dto.CommentCursorResponse;
import com.piumteo.server.domain.comment.dto.CommentMutationResponse;
import com.piumteo.server.domain.comment.dto.CommentResponse;
import com.piumteo.server.domain.comment.dto.CreateGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.CreateMemberCommentRequest;
import com.piumteo.server.domain.comment.dto.DeleteGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.UpdateGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.UpdateMemberCommentRequest;
import com.piumteo.server.domain.comment.entity.PlaceComment;
import com.piumteo.server.domain.comment.exception.CommentCode;
import com.piumteo.server.domain.comment.exception.CommentException;
import com.piumteo.server.domain.comment.repository.PlaceCommentRepository;
import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.place.service.PlaceService;
import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private static final int DEFAULT_COMMENT_PAGE_SIZE = 10;
    private static final int MAX_COMMENT_PAGE_SIZE = 50;

    private final PlaceCommentRepository placeCommentRepository;
    private final PlaceService placeService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CommentMutationResponse createMemberComment(
            Long placeId,
            Long userId,
            CreateMemberCommentRequest request
    ) {
        // TODO 인증 구현 후 Controller의 @AuthenticationPrincipal에서 userId를 꺼내 전달
        Place place = placeService.getActivePlace(placeId);
        User user = userService.getActiveUser(userId);

        PlaceComment comment = PlaceComment.createMemberComment(
                place,
                user,
                user.getNickname(),
                request.content()
        );

        PlaceComment savedComment = placeCommentRepository.save(comment);
        return CommentMutationResponse.from(savedComment);
    }

    @Transactional
    public CommentMutationResponse createGuestComment(
            Long placeId,
            CreateGuestCommentRequest request
    ) {
        Place place = placeService.getActivePlace(placeId);

        String encodedPassword = passwordEncoder.encode(request.guestPassword());

        PlaceComment comment = PlaceComment.createGuestComment(
                place,
                request.displayNickname(),
                encodedPassword,
                request.content()
        );

        PlaceComment savedComment = placeCommentRepository.save(comment);
        return CommentMutationResponse.from(savedComment);
    }

    @Transactional
    public CommentMutationResponse updateMemberComment(
            Long placeId,
            Long commentId,
            Long userId,
            UpdateMemberCommentRequest request
    ) {
        // TODO 인증 구현 후 Controller의 @AuthenticationPrincipal에서 userId를 꺼내 전달
        PlaceComment comment = getActiveCommentInPlace(placeId, commentId);

        if (!comment.isWrittenByMember(userId)) {
            throw new CommentException(CommentCode.COMMENT_UPDATE_FORBIDDEN);
        }

        comment.updateContent(request.content());

        return CommentMutationResponse.from(comment);
    }

    @Transactional
    public CommentMutationResponse updateGuestComment(
            Long placeId,
            Long commentId,
            UpdateGuestCommentRequest request
    ) {
        PlaceComment comment = getActiveCommentInPlace(placeId, commentId);

        if (!comment.isGuestComment()) {
            throw new CommentException(CommentCode.COMMENT_UPDATE_FORBIDDEN);
        }

        validateGuestPassword(comment, request.guestPassword());

        comment.updateContent(request.content());

        return CommentMutationResponse.from(comment);
    }

    @Transactional
    public void deleteMemberComment(
            Long placeId,
            Long commentId,
            Long userId
    ) {
        // TODO 인증 구현 후 Controller의 @AuthenticationPrincipal에서 userId를 꺼내 전달
        PlaceComment comment = getActiveCommentInPlace(placeId, commentId);

        if (!comment.isWrittenByMember(userId)) {
            throw new CommentException(CommentCode.COMMENT_DELETE_FORBIDDEN);
        }

        comment.delete();
    }

    @Transactional
    public void deleteGuestComment(
            Long placeId,
            Long commentId,
            DeleteGuestCommentRequest request
    ) {
        PlaceComment comment = getActiveCommentInPlace(placeId, commentId);

        if (!comment.isGuestComment()) {
            throw new CommentException(CommentCode.COMMENT_DELETE_FORBIDDEN);
        }

        validateGuestPassword(comment, request.guestPassword());

        comment.delete();
    }

    public CommentCursorResponse getComments(
            Long placeId,
            Long cursorId,
            Integer size,
            Long currentUserId
    ) {
        placeService.getActivePlace(placeId);

        int requestSize = normalizeSize(size);
        Pageable pageable = PageRequest.of(0, requestSize + 1);

        List<PlaceComment> fetchedComments = findCommentsByCursor(
                placeId,
                cursorId,
                pageable
        );

        boolean hasNext = fetchedComments.size() > requestSize;

        List<PlaceComment> comments = hasNext
                ? fetchedComments.subList(0, requestSize)
                : fetchedComments;

        Long nextCursor = hasNext && !comments.isEmpty()
                ? comments.get(comments.size() - 1).getId()
                : null;

        List<CommentResponse> responses = comments.stream()
                .map(comment -> CommentResponse.from(
                        comment,
                        comment.isWrittenByMember(currentUserId)
                ))
                .toList();

        return new CommentCursorResponse(
                responses,
                nextCursor,
                hasNext
        );
    }

    private List<PlaceComment> findCommentsByCursor(
            Long placeId,
            Long cursorId,
            Pageable pageable
    ) {
        if (cursorId == null) {
            return placeCommentRepository.findByPlace_IdAndDeletedAtIsNullOrderByIdDesc(
                    placeId,
                    pageable
            );
        }

        return placeCommentRepository.findByPlace_IdAndDeletedAtIsNullAndIdLessThanOrderByIdDesc(
                placeId,
                cursorId,
                pageable
        );
    }

    private int normalizeSize(Integer size) {
        if (size == null || size < 1) {
            return DEFAULT_COMMENT_PAGE_SIZE;
        }

        return Math.min(size, MAX_COMMENT_PAGE_SIZE);
    }

    private PlaceComment getActiveCommentInPlace(
            Long placeId,
            Long commentId
    ) {
        placeService.getActivePlace(placeId);

        return placeCommentRepository.findByIdAndPlace_IdAndDeletedAtIsNull(
                        commentId,
                        placeId
                )
                .orElseThrow(() -> new CommentException(CommentCode.COMMENT_NOT_FOUND));
    }

    private void validateGuestPassword(
            PlaceComment comment,
            String rawPassword
    ) {
        boolean passwordMatches = passwordEncoder.matches(
                rawPassword,
                comment.getGuestPasswordHash()
        );

        if (!passwordMatches) {
            throw new CommentException(CommentCode.COMMENT_PASSWORD_MISMATCH);
        }
    }
}