package com.piumteo.server.domain.comment.service;

import com.piumteo.server.domain.comment.dto.CommentResponse;
import com.piumteo.server.domain.comment.dto.CreateCommentResponse;
import com.piumteo.server.domain.comment.dto.CreateGuestCommentRequest;
import com.piumteo.server.domain.comment.dto.CreateMemberCommentRequest;
import com.piumteo.server.domain.comment.dto.DeleteGuestCommentRequest;
import com.piumteo.server.domain.comment.entity.PlaceComment;
import com.piumteo.server.domain.comment.repository.PlaceCommentRepository;
import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.place.service.PlaceService;
import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.domain.user.service.UserService;
import com.piumteo.server.global.exception.BusinessException;
import com.piumteo.server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

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

    public List<CommentResponse> getComments(Long placeId) {
        placeService.getActivePlace(placeId);

        return placeCommentRepository
                .findAllByPlace_IdAndDeletedAtIsNullOrderByCreatedAtAsc(placeId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    @Transactional
    public void deleteMemberComment(
            Long commentId,
            Long userId
    ) {
        PlaceComment comment = getActiveComment(commentId);

        if (!comment.isWrittenByMember(userId)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_COMMENT_DELETE);
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
            throw new BusinessException(ErrorCode.UNAUTHORIZED_COMMENT_DELETE);
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.guestPassword(),
                comment.getGuestPasswordHash()
        );

        if (!passwordMatches) {
            throw new BusinessException(ErrorCode.INVALID_GUEST_PASSWORD);
        }

        comment.delete();
    }

    private PlaceComment getActiveComment(Long commentId) {
        return placeCommentRepository.findByIdAndDeletedAtIsNull(commentId)
                .orElseThrow(() -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));
    }
}