package com.luke.springstudy.domain.insurance.entity;

import com.luke.springstudy.domain.insurance.entity.enums.VehicleGrade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Entity
@Table(name = "vehicle")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "weekly_distance >= 0")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @Column(name = "registration_no", nullable = false, unique = true, length = 30)
    private String registrationNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_vehicle_customer"))
    private Customer customer;

    @Column(name = "manufacture_year", nullable = false)
    private Integer manufactureYear;

    @Column(name = "vehicle_usage", nullable = false, length = 30)
    private String vehicleUsage;

    @Column(name = "weekly_distance", nullable = false, precision = 10, scale = 2)
    private BigDecimal weeklyDistance;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_grade", nullable = false, length = 20)
    private VehicleGrade vehicleGrade;
}
