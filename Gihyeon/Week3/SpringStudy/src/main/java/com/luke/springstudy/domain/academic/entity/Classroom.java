package com.luke.springstudy.domain.academic.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "classroom",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_classroom", columnNames = {"building_name", "room_no"})
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Classroom {

    @Id
    @Column(name = "classroom_id")
    private Long id;

    @Column(name = "building_name", nullable = false, length = 100)
    private String buildingName;

    @Column(name = "room_no", nullable = false, length = 20)
    private String roomNo;
}
