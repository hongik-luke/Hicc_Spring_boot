package com.piumteo.server.domain.place.repository;

import com.piumteo.server.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> findByIdAndDeletedAtIsNull(Long id);

    List<Place> findByDeletedAtIsNullAndLatitudeBetweenAndLongitudeBetween(
            BigDecimal minLatitude,
            BigDecimal maxLatitude,
            BigDecimal minLongitude,
            BigDecimal maxLongitude
    );
}