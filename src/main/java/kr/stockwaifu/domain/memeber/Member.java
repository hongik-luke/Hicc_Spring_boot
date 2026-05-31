package kr.stockwaifu.domain.memeber; // (혹은 본인의 member 패키지 경로)

import jakarta.persistence.*;
import kr.stockwaifu.domain.asset.Asset;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor; // 💡 추가
import lombok.AccessLevel;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder // 1. 빌더를 클래스 레벨로 이동!
@AllArgsConstructor // 2. 클래스 레벨 빌더를 쓰기 위해 전체 생성자 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String nickname;

    private String password;

    private Long totalAsset; // 보유 현금

    private Long representativeCharacterId; // 대표 캐릭터 ID

    @OneToMany(mappedBy = "member")
    private List<Asset> assets = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    // 기존 생성자 위에 있던 @Builder는 지우거나, 생성자 자체를 없애도 @AllArgsConstructor 덕분에 잘 작동합니다.
}
