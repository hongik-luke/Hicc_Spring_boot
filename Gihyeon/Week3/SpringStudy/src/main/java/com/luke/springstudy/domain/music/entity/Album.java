package com.luke.springstudy.domain.music.entity;

import com.luke.springstudy.domain.music.entity.enums.AlbumFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity
@Table(name = "album")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "trim(title) <> '' AND trim(album_identifier) <> ''")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "copyright_date", nullable = false)
    private LocalDate copyrightDate;

    @Column(name = "album_identifier", nullable = false, unique = true, length = 100)
    private String albumIdentifier;

    @Enumerated(EnumType.STRING)
    @Column(name = "format", nullable = false, length = 20)
    private AlbumFormat format;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producer_ssn", nullable = false, referencedColumnName = "ssn", foreignKey = @ForeignKey(name = "fk_album_producer"))
    private Musician producer;
}
