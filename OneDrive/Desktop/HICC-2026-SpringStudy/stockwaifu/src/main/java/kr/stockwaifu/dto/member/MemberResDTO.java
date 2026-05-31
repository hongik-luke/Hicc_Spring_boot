package kr.stockwaifu.dto.member;

import lombok.Builder;
import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinDTO(
            Long memberId,
            LocalDateTime createdAt) {
    }
}