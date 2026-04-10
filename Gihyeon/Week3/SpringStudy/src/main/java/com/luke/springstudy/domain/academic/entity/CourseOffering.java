package com.luke.springstudy.domain.academic.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(
        name = "course_offering",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_offering", columnNames = {"course_id", "term_id", "section_no"}),
                @UniqueConstraint(name = "uq_offering_id_term_instructor", columnNames = {"offering_id", "term_id", "instructor_id"}),
                @UniqueConstraint(name = "uq_offering_id_term", columnNames = {"offering_id", "term_id"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "section_no > 0")
public class CourseOffering {

    @Id
    @Column(name = "offering_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "fk_offering_course"))
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false, foreignKey = @ForeignKey(name = "fk_offering_instructor"))
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id", nullable = false, foreignKey = @ForeignKey(name = "fk_offering_term"))
    private Term term;

    @Column(name = "section_no", nullable = false)
    private Integer sectionNo;
}
