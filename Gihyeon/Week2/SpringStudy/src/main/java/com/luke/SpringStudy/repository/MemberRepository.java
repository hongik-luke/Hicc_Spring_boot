package com.luke.SpringStudy.repository;

import com.luke.SpringStudy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}