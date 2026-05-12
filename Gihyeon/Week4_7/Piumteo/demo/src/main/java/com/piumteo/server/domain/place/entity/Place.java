package com.piumteo.server.domain.place.entity;

import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Entity
@Table(name = "places")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "created_by_user_id", nullable = false)
    private User createdBy;

    @Column(name = "place_name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "place_type", nullable = false, length = 30)
    private PlaceType type;

    @Column(name = "latitude", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(name = "location_description", length = 255)
    private String locationDescription;

    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;

    public Place(
            User createdBy,
            String name,
            PlaceType type,
            BigDecimal latitude,
            BigDecimal longitude,
            String locationDescription
    ) {
        this.createdBy = createdBy;
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationDescription = locationDescription;
    }

    public void update(
            String name,
            PlaceType type,
            BigDecimal latitude,
            BigDecimal longitude,
            String locationDescription
    ) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationDescription = locationDescription;
    }

    public void delete() {
        this.deletedAt = OffsetDateTime.now();
    }
}