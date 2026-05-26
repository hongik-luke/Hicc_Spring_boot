package com.luke.springstudy.domain.academic.entity.id;

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
public class OfferingScheduleId implements Serializable {

    @Column(name = "offering_id")
    private Long offeringId;

    @Column(name = "time_slot_id")
    private Long timeSlotId;
}
