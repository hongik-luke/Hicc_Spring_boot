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
public class AlbumTrackId implements Serializable {

    @Column(name = "album_id")
    private Long albumId;

    @Column(name = "track_no")
    private Integer trackNo;
}
