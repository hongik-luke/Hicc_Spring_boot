package com.piumteo.server.domain.comment.entity;

import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@Entity
@Table(name = "place_comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_author_type", nullable = false, length = 20)
    private CommentAuthorType authorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_user_id")
    private User member;

    @Column(name = "display_nickname", nullable = false, length = 50)
    private String displayNickname;

    @Column(name = "guest_password_hash")
    private String guestPasswordHash;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    public static PlaceComment createMemberComment(
            Place place,
            User member,
            String displayNickname,
            String content
    ) {
        PlaceComment comment = new PlaceComment();
        comment.place = place;
        comment.authorType = CommentAuthorType.MEMBER;
        comment.member = member;
        comment.displayNickname = displayNickname;
        comment.content = content;
        comment.guestPasswordHash = null;
        return comment;
    }

    public static PlaceComment createGuestComment(
            Place place,
            String displayNickname,
            String guestPasswordHash,
            String content
    ) {
        PlaceComment comment = new PlaceComment();
        comment.place = place;
        comment.authorType = CommentAuthorType.GUEST;
        comment.member = null;
        comment.displayNickname = displayNickname;
        comment.guestPasswordHash = guestPasswordHash;
        comment.content = content;
        return comment;
    }

    public void delete() {
        this.deletedAt = OffsetDateTime.now();
    }

    public boolean isDeleted() {
        return this.deletedAt != null;
    }

    public boolean isWrittenByMember(Long userId) {
        return this.member != null && this.member.getId().equals(userId);
    }

    public boolean isGuestComment() {
        return this.authorType == CommentAuthorType.GUEST;
    }
}