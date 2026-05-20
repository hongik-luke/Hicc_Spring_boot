package com.piumteo.server.domain.user.repository;

import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.domain.user.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndStatusAndDeletedAtIsNull(
            Long id,
            UserStatus status
    );

    Optional<User> findByEmailAndDeletedAtIsNull(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);
}