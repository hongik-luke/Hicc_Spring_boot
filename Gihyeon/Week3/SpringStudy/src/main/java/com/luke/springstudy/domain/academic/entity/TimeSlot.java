package com.luke.springstudy.domain.academic.entity;

import com.luke.springstudy.domain.academic.entity.enums.DayOfWeekType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(
        name = "time_slot",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_time_slot", columnNames = {"day_of_week", "period"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "period > 0")
public class TimeSlot {

    @Id
    @Column(name = "time_slot_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false, length = 10)
    private DayOfWeekType dayOfWeek;

    @Column(name = "period", nullable = false)
    private Integer period;
}
