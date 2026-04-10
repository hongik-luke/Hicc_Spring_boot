package com.luke.springstudy.domain.insurance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insurance_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
