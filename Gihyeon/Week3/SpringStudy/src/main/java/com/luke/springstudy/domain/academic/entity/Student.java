package com.luke.springstudy.domain.academic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "student")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Student {

    @Id
    @Column(name = "student_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", length = 255)
    private String address;
}
