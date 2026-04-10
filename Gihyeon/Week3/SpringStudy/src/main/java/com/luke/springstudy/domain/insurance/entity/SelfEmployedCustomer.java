package com.luke.springstudy.domain.insurance.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "self_employed_customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SelfEmployedCustomer {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_self_customer"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "occupation_id", nullable = false, foreignKey = @ForeignKey(name = "fk_self_occupation"))
    private Occupation occupation;
}
