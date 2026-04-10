package com.luke.springstudy.domain.music.entity;

import com.luke.springstudy.domain.music.entity.id.MusicianInstrumentId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musician_instrument")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MusicianInstrument {

    @EmbeddedId
    private MusicianInstrumentId id;

    @MapsId("musicianSsn")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "musician_ssn", referencedColumnName = "ssn", nullable = false, foreignKey = @ForeignKey(name = "fk_mi_musician"))
    private Musician musician;

    @MapsId("instrumentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrument_id", nullable = false, foreignKey = @ForeignKey(name = "fk_mi_instrument"))
    private Instrument instrument;
}
