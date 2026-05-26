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
public class SongInstrumentId implements Serializable {

    @Column(name = "song_id")
    private Long songId;

    @Column(name = "instrument_id")
    private Long instrumentId;
}
