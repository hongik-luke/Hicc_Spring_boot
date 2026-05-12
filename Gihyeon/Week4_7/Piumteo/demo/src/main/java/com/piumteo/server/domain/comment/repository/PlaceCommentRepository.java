package com.piumteo.server.domain.comment.repository;

import com.piumteo.server.domain.comment.entity.PlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceCommentRepository extends JpaRepository<PlaceComment, Long> {

    Optional<PlaceComment> findByIdAndDeletedAtIsNull(Long id);

    List<PlaceComment> findAllByPlace_IdAndDeletedAtIsNullOrderByCreatedAtAsc(
            Long placeId
    );

    long countByPlace_IdAndDeletedAtIsNull(Long placeId);
}