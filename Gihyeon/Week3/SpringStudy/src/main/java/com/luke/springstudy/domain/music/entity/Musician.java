package com.luke.springstudy.domain.music.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "musician")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "trim(name) <> '' AND ssn ~ '^[0-9]{3}-?[0-9]{2}-?[0-9]{4}$'")
public class Musician {

    @Id
    @Column(name = "ssn", length = 11)
    private String ssn;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "house_id", nullable = false, foreignKey = @ForeignKey(name = "fk_musician_house"))
    private House house;
}
