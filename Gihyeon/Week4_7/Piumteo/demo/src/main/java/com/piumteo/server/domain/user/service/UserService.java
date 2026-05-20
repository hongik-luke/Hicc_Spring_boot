package com.piumteo.server.domain.user.service;

import com.piumteo.server.domain.user.entity.User;
import com.piumteo.server.domain.user.entity.UserStatus;
import com.piumteo.server.domain.user.repository.UserRepository;
import com.piumteo.server.global.exception.BusinessException;
import com.piumteo.server.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User getActiveUser(Long userId) {
        return userRepository.findByIdAndStatusAndDeletedAtIsNull(
                        userId,
                        UserStatus.ACTIVE
                )
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    public void validateEmailNotDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    public void validateNicknameNotDuplicated(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        }
    }
}