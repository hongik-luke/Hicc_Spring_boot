package com.luke.springstudy.domain.music.entity;

import com.luke.springstudy.domain.music.entity.id.AlbumTrackId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(
        name = "album_track",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_album_track_song", columnNames = {"song_id"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "track_no >= 1")
public class AlbumTrack {

    @EmbeddedId
    private AlbumTrackId id;

    @MapsId("albumId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "album_id", nullable = false, foreignKey = @ForeignKey(name = "fk_at_album"))
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "song_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "fk_at_song"))
    private Song song;
}
