package kr.stockwaifu.converter;

import kr.stockwaifu.domain.memeber.Member;
import kr.stockwaifu.domain.memeber.Role;
import kr.stockwaifu.dto.member.MemberReqDTO;
import kr.stockwaifu.dto.member.MemberResDTO;

public class MemberConverter {

    // DTO, Salted Password, Role -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role) {
        return Member.builder()
                .email(dto.email()) // 추가된 코드
                .password(password) // 추가된 코드
                .role(role) // 추가된 코드
                .build();
    }

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(Member member) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(MemberReqDTO.JoinDTO dto) {
        return Member.builder()
                .email(dto.email())
                .nickname(dto.name()) // 엔티티의 nickname 필드에 DTO의 name을 매핑!
                .password("초기비밀번호셋팅") // 필요시 MemberReqDTO.JoinDTO에 password 필드를 추가하는 것이 좋습니다.
                .totalAsset(0L) // 가입 시 기본 현금 0원 세팅
                .build();
    }
}