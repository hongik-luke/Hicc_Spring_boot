package com.luke.springstudy.domain.insurance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeCustomer {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_employee_customer"))
    private Customer customer;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;
}
