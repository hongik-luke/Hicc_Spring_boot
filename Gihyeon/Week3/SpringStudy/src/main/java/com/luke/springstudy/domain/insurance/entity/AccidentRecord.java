package com.luke.springstudy.domain.insurance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "accident_record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccidentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accident_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false, foreignKey = @ForeignKey(name = "fk_accident_vehicle"))
    private Vehicle vehicle;

    @Column(name = "accident_datetime", nullable = false)
    private LocalDateTime accidentDatetime;

    @Column(name = "accident_description", columnDefinition = "TEXT")
    private String accidentDescription;
}
