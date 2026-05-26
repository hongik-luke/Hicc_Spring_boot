package com.luke.springstudy.domain.academic.entity;

import com.luke.springstudy.domain.academic.entity.id.EnrollmentId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false, foreignKey = @ForeignKey(name = "fk_enrollment_student"))
    private Student student;

    @MapsId("offeringId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "offering_id", nullable = false, foreignKey = @ForeignKey(name = "fk_enrollment_offering"))
    private CourseOffering courseOffering;

    @Column(name = "grade", length = 5)
    private String grade;

    @Column(name = "is_retake", nullable = false)
    private Boolean isRetake = false;
}
