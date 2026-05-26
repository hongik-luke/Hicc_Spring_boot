package com.luke.springstudy.domain.music.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class MusicianInstrumentId implements Serializable {

    @Column(name = "musician_ssn", length = 11)
    private String musicianSsn;

    @Column(name = "instrument_id")
    private Long instrumentId;
}
