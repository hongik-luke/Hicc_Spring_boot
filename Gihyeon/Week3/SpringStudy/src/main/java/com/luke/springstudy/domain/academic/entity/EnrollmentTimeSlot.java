package com.luke.springstudy.domain.academic.entity;

import com.luke.springstudy.domain.academic.entity.id.EnrollmentTimeSlotId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "enrollment_time_slot",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_enrollment_term_time", columnNames = {"student_id", "term_id", "time_slot_id"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnrollmentTimeSlot {

    @EmbeddedId
    private EnrollmentTimeSlotId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "student_id", referencedColumnName = "student_id", insertable = false, updatable = false),
            @JoinColumn(name = "offering_id", referencedColumnName = "offering_id", insertable = false, updatable = false)
    }, foreignKey = @ForeignKey(name = "fk_enrollment_time_offering"))
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "offering_id", referencedColumnName = "offering_id", insertable = false, updatable = false),
            @JoinColumn(name = "time_slot_id", referencedColumnName = "time_slot_id", insertable = false, updatable = false)
    }, foreignKey = @ForeignKey(name = "fk_enrollment_time_schedule"))
    private OfferingSchedule offeringSchedule;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id", nullable = false, insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_enrollment_time_term"))
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns(value = {
            @JoinColumn(name = "offering_id", referencedColumnName = "offering_id", insertable = false, updatable = false),
            @JoinColumn(name = "term_id", referencedColumnName = "term_id", insertable = false, updatable = false)
    }, foreignKey = @ForeignKey(name = "fk_enrollment_time_offering_term"))
    private CourseOffering courseOffering;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "fk_enrollment_time_student"))
    private Student student;
}