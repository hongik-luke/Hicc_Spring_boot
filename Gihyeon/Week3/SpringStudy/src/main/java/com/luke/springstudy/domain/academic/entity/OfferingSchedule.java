package com.luke.springstudy.domain.academic.entity;

import com.luke.springstudy.domain.academic.entity.id.OfferingScheduleId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "offering_schedule",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_schedule_term_time_classroom", columnNames = {"term_id", "time_slot_id", "classroom_id"}),
                @UniqueConstraint(name = "uq_schedule_term_time_instructor", columnNames = {"term_id", "time_slot_id", "instructor_id"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OfferingSchedule {

    @EmbeddedId
    private OfferingScheduleId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "offering_id", referencedColumnName = "offering_id", insertable = false, updatable = false),
            @JoinColumn(name = "term_id", referencedColumnName = "term_id", insertable = false, updatable = false),
            @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", insertable = false, updatable = false)
    }, foreignKey = @ForeignKey(name = "fk_schedule_offering_term_instructor"))
    private CourseOffering courseOffering;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "time_slot_id", nullable = false, insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_schedule_time_slot"))
    private TimeSlot timeSlot;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "classroom_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_schedule_classroom"))
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id", nullable = false, insertable = false, updatable = false)
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false, insertable = false, updatable = false)
    private Instructor instructor;
}