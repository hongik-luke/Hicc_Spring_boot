package com.boardgame_guide.erp.domain.member.entity;

import jakarta.persistence.*;
        import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "member_id", length = 50)
    private String memberId;

    @Column(name = "account_id", nullable = false, updatable = false)
    private UUID accountId;

    @Column(name = "student_number", length = 20)
    private String studentNumber;

    @Column(name = "member_name", length = 50)
    private String memberName;

    @Column(name = "dept_name", length = 50)
    private String deptName;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public Member(String memberId, UUID accountId, String studentNumber, String memberName, String deptName) {
        this.memberId = memberId;
        this.accountId = accountId;
        this.studentNumber = studentNumber;
        this.memberName = memberName;
        this.deptName = deptName;
    }
}