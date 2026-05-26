package com.luke.springstudy.domain.music.entity;

import com.luke.springstudy.domain.music.entity.id.SongInstrumentId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "song_instrument")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SongInstrument {

    @EmbeddedId
    private SongInstrumentId id;

    @MapsId("songId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false, foreignKey = @ForeignKey(name = "fk_si_song"))
    private Song song;

    @MapsId("instrumentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrument_id", nullable = false, foreignKey = @ForeignKey(name = "fk_si_instrument"))
    private Instrument instrument;
}
