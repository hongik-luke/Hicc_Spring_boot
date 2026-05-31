package kr.stockwaifu.repository;

import kr.stockwaifu.domain.memeber.MemberCategory; // 회원-카테고리 다대다 매핑 엔티티 가정
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory, Long> {
}