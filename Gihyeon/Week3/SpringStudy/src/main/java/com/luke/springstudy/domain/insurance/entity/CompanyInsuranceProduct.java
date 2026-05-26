package com.luke.springstudy.domain.insurance.entity;

import com.luke.springstudy.domain.insurance.entity.enums.SaleStatus;
import com.luke.springstudy.domain.insurance.entity.enums.VehicleGrade;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "company_insurance_product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "min_risk_level BETWEEN 1 AND 5 AND max_risk_level BETWEEN 1 AND 5 AND min_risk_level <= max_risk_level")
public class CompanyInsuranceProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cip_company"))
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cip_product"))
    private InsuranceProduct product;

    @Column(name = "sale_name", nullable = false, length = 100)
    private String saleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "sale_status", nullable = false, length = 20)
    private SaleStatus saleStatus;

    @Column(name = "min_risk_level", nullable = false)
    private Integer minRiskLevel;

    @Column(name = "max_risk_level", nullable = false)
    private Integer maxRiskLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "allowed_vehicle_grade", nullable = false, length = 20)
    private VehicleGrade allowedVehicleGrade;
}
