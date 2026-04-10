package com.luke.springstudy.domain.insurance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "occupation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "risk_level BETWEEN 1 AND 5")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "occupation_id")
    private Long id;

    @Column(name = "occupation_name", nullable = false, unique = true, length = 100)
    private String occupationName;

    @Column(name = "risk_level", nullable = false)
    private Integer riskLevel;
}
