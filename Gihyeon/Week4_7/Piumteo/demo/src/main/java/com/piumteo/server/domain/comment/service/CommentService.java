package com.piumteo.server.domain.comment.service;

import com.piumteo.server.domain.comment.dto.CommentCursorResponse;
import com.piumteo.server.domain.comment.dto.CommentResponse;
import com.piumteo.server.domain.comment.dto.CreateCommentResponse;
import com.piumteo.server.domain.comment.dto.CreateGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.CreateMemberCommentRequest;
import com.piumteo.server.domain.comment.dto.DeleteGuestCommentRequest;
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
    public CreateCommentResponse createMemberComment(
            Long placeId,
            Long userId,
            CreateMemberCommentRequest request
    ) {
        Place place = placeService.getActivePlace(placeId);
        User user = userService.getActiveUser(userId);

        PlaceComment comment = PlaceComment.createMemberComment(
                place,
                user,
                user.getNickname(),
                request.content()
        );

        PlaceComment savedComment = placeCommentRepository.save(comment);

        return CreateCommentResponse.from(savedComment.getId());
    }

    @Transactional
    public CreateCommentResponse createGuestComment(
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

        return CreateCommentResponse.from(savedComment.getId());
    }

    @Transactional
    public void deleteMemberComment(
            Long commentId,
            Long userId
    ) {
        PlaceComment comment = getActiveComment(commentId);

        if (!comment.isWrittenByMember(userId)) {
            throw new CommentException(CommentCode.UNAUTHORIZED_COMMENT_DELETE);
        }

        comment.delete();
    }

    @Transactional
    public void deleteGuestComment(
            Long commentId,
            DeleteGuestCommentRequest request
    ) {
        PlaceComment comment = getActiveComment(commentId);

        if (!comment.isGuestComment()) {
            throw new CommentException(CommentCode.UNAUTHORIZED_COMMENT_DELETE);
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.guestPassword(),
                comment.getGuestPasswordHash()
        );

        if (!passwordMatches) {
            throw new CommentException(CommentCode.INVALID_GUEST_PASSWORD);
        }

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

    private PlaceComment getActiveComment(Long commentId) {
        return placeCommentRepository.findByIdAndDeletedAtIsNull(commentId)
                .orElseThrow(() -> new CommentException(CommentCode.COMMENT_NOT_FOUND));
    }
}