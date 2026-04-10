package com.luke.springstudy.domain.academic.entity;

import com.luke.springstudy.domain.academic.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "instructor")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Check(constraints = "age > 0")
public class Instructor {

    @Id
    @Column(name = "instructor_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;
}
