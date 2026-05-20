package com.piumteo.server.domain.reaction.entity;

import com.piumteo.server.domain.place.entity.Place;
import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "place_reactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceReaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_reaction_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_author_type", nullable = false, length = 20)
    private ReactionAuthorType authorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_user_id")
    private User member;

    @Column(name = "guest_key_hash")
    private String guestKeyHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_type", nullable = false, length = 20)
    private ReactionType reactionType;

    @Column(name = "reaction_hour_key", nullable = false)
    private Long reactionHourKey;

    public static PlaceReaction createMemberReaction(
            Place place,
            User member,
            ReactionType reactionType,
            Long reactionHourKey
    ) {
        PlaceReaction reaction = new PlaceReaction();
        reaction.place = place;
        reaction.authorType = ReactionAuthorType.MEMBER;
        reaction.member = member;
        reaction.guestKeyHash = null;
        reaction.reactionType = reactionType;
        reaction.reactionHourKey = reactionHourKey;
        return reaction;
    }

    public static PlaceReaction createGuestReaction(
            Place place,
            String guestKeyHash,
            ReactionType reactionType,
            Long reactionHourKey
    ) {
        PlaceReaction reaction = new PlaceReaction();
        reaction.place = place;
        reaction.authorType = ReactionAuthorType.GUEST;
        reaction.member = null;
        reaction.guestKeyHash = guestKeyHash;
        reaction.reactionType = reactionType;
        reaction.reactionHourKey = reactionHourKey;
        return reaction;
    }

    public void changeReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

    public boolean isSameReaction(ReactionType reactionType) {
        return this.reactionType == reactionType;
    }

    public void changeReaction(ReactionType reactionType, Long reactionHourKey) {
        this.reactionType = reactionType;
        this.reactionHourKey = reactionHourKey;
    }
}