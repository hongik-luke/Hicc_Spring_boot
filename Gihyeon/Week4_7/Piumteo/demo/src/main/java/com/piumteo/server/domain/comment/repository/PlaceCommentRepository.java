package com.piumteo.server.domain.comment.repository;

import com.piumteo.server.domain.comment.entity.PlaceComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaceCommentRepository extends JpaRepository<PlaceComment, Long> {

    Optional<PlaceComment> findByIdAndDeletedAtIsNull(Long id);

    Optional<PlaceComment> findByIdAndPlace_IdAndDeletedAtIsNull(
            Long id,
            Long placeId
    );

    List<PlaceComment> findByPlace_IdAndDeletedAtIsNullOrderByIdDesc(
            Long placeId,
            Pageable pageable
    );

    List<PlaceComment> findByPlace_IdAndDeletedAtIsNullAndIdLessThanOrderByIdDesc(
            Long placeId,
            Long cursorId,
            Pageable pageable
    );

    long countByPlace_IdAndDeletedAtIsNull(Long placeId);
}