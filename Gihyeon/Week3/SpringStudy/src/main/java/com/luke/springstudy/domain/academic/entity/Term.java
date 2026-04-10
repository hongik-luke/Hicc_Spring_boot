package com.luke.springstudy.domain.academic.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(
        name = "term",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_term", columnNames = {"year", "semester"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "year >= 2000 AND semester IN (1, 2)")
public class Term {

    @Id
    @Column(name = "term_id")
    private Long id;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "semester", nullable = false)
    private Integer semester;
}
