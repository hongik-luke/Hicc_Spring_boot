package com.luke.springstudy.domain.music.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(
        name = "instrument",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_instrument_name_key", columnNames = {"name", "musical_key"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "trim(name) <> '' AND trim(musical_key) <> ''")
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instrument_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "musical_key", nullable = false, length = 30)
    private String musicalKey;
}
