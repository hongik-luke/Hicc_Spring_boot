package com.luke.springstudy.domain.music.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "house")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    @Column(name = "address", nullable = false, unique = true, length = 255)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 30)
    private String phoneNumber;
}
