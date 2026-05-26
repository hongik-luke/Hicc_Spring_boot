package com.luke.springstudy.domain.music.entity;

import com.luke.springstudy.domain.music.entity.id.MusicianSongId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "musician_song")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MusicianSong {

    @EmbeddedId
    private MusicianSongId id;

    @MapsId("musicianSsn")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "musician_ssn", referencedColumnName = "ssn", nullable = false, foreignKey = @ForeignKey(name = "fk_ms_musician"))
    private Musician musician;

    @MapsId("songId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ms_song"))
    private Song song;
}
