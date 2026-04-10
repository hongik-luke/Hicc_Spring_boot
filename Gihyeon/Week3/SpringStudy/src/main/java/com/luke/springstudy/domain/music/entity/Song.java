package com.luke.springstudy.domain.music.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "song")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "trim(title) <> '' AND trim(author) <> ''")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "author", nullable = false, length = 200)
    private String author;
}
