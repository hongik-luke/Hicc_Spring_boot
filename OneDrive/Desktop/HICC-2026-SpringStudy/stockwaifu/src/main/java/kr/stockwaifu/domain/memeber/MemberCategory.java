package kr.stockwaifu.domain.memeber; // 혹은 domain.memeber (본인 오타 패키지명에 맞추세요)

import jakarta.persistence.*;
import kr.stockwaifu.domain.stock.StockCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder // 이게 있어야 .builder() 에러가 사라짐
@AllArgsConstructor // 💡 Builder를 쓰려면 전체 생성자도 필요합니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_category_id")
    private StockCategory stockCategory;
}