package kr.stockwaifu.service;

import kr.stockwaifu.converter.MemberConverter;
import kr.stockwaifu.domain.memeber.Member;
import kr.stockwaifu.domain.memeber.Role;
import kr.stockwaifu.dto.member.MemberReqDTO;
import kr.stockwaifu.dto.member.MemberResDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto) {

        // 솔트된 비밀번호 생성
        String salt = passwordEncoder.encode(dto.password());

        // 사용자 생성: 유저 / 관리자는 따로 API 만들어서 관리
        Member member = MemberConverter.toMember(dto, salt, Role.ROLE_USER);

        return MemberResDTO.JoinDTO.builder()
                .memberId(1L) // 임의 아이디 설정
                .build();
    }
}