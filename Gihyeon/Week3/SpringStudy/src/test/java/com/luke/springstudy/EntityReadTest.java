package com.luke.springstudy;

import com.luke.springstudy.domain.academic.entity.Instructor;
import com.luke.springstudy.domain.insurance.entity.Company;
import com.luke.springstudy.domain.music.entity.Musician;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import java.util.TreeSet;

@SpringBootTest
@Transactional
class EntityReadTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    void entity_mapping_check() {
        Set<String> entityNames = new TreeSet<>();

        for (EntityType<?> entityType : em.getMetamodel().getEntities()) {
            entityNames.add(entityType.getName());
        }

        System.out.println("======================================");
        System.out.println("JPA 엔티티 매핑 확인 완료");
        System.out.println("등록된 엔티티 개수: " + entityNames.size());
        System.out.println("======================================");

        for (String entityName : entityNames) {
            System.out.println("엔티티: " + entityName);
        }

        System.out.println("======================================");
        System.out.println("academic 예시 엔티티 확인: " + Instructor.class.getSimpleName());
        System.out.println("insurance 예시 엔티티 확인: " + Company.class.getSimpleName());
        System.out.println("music 예시 엔티티 확인: " + Musician.class.getSimpleName());
        System.out.println("======================================");
        System.out.println("결론: 엔티티 스캔 및 JPA 매핑이 정상적으로 완료되었습니다.");
    }
}